package community_user_profile;

import community.Community;
import community.CommunityRepository;
import community.CommunityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.User;
import user.UserController;
import user.UserRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value = "/communityUserProfile", produces = "application/json")
public class CommunityUserProfileController {

    @Autowired
    CommunityUserProfileRepository communityUserProfileRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommunityRepository communityRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCommunityUserProfile(@RequestParam String username, @RequestParam String commName) throws ExecutionException, InterruptedException {
        Future<User> user = userRepository.findUserByUsername(username);

        Future<Community> community = communityRepository.findCommunityByName(commName);

        Future<CommunityUserProfile> communityUserProfile = communityUserProfileRepository.findCommunityUserProfile(user.get().getUid(),
                community.get().getCid());

        try{
            communityUserProfile.get().getDescription();
            return ResponseEntity.status(HttpStatus.OK).body(communityUserProfile.get());
        } 
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not be found");
        }
    }
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCommunityUserProfile(@RequestParam long uid, @RequestParam long cid) {
        Future<CommunityUserProfile> communityUserProfile = communityUserProfileRepository.findCommunityUserProfile(uid, cid);

        try{
            communityUserProfile.get().getDescription();
            return ResponseEntity.status(HttpStatus.OK).body(communityUserProfile.get());
        } 
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not be found");
        }
    }
//_________________________________________________
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCommunityIds(@RequestParam long uid) {
        List<Long> communityIds = communityUserProfileRepository.findCommunityFromUser(uid);
        CommunityController commController = new CommunityController();
        
        List<Community> comms = null;
        commController.getCommunityProfiles(communityIds);
        
        return ResponseEntity.status(HttpStatus.OK).body(comms);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getUserIds(@RequestParam long cid) {
        List<Long> userIds = communityUserProfileRepository.findUserFromCommunity(cid);
        UserController userController = new UserController();
        
        List<User> users = null;
        userController.getUserProfiles(userIds);
        
        return ResponseEntity.status(HttpStatus.OK).body(userIds);
    }
    
//___________________________________________________________________
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity post(@RequestBody CommunityUserProfile communityUserProfile) {
    	communityUserProfileRepository.save(communityUserProfile);
    	
        return ResponseEntity.status(HttpStatus.OK).body("Posted");
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity put(@RequestBody CommunityUserProfile communityUserProfile) {
    	communityUserProfileRepository.save(communityUserProfile);
    	
        return ResponseEntity.status(HttpStatus.OK).body("Edited");
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody CommunityUserProfile communityUserProfile) {
    	ResponseEntity res = getCommunityUserProfile(communityUserProfile.getUid(), communityUserProfile.getCid());
    	
    	if(res.getStatusCode() == HttpStatus.OK) {
    		CommunityUserProfile temp = (CommunityUserProfile) res.getBody();
    		communityUserProfileRepository.delete(temp);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    	}
    	
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find user to delete");
    }
}
