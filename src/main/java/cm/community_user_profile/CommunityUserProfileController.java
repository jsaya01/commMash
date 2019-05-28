package cm.community_user_profile;

import cm.community.Community;
import cm.community.CommunityController;
import cm.community.CommunityRepository;
import cm.user.User;
import cm.user.UserController;
import cm.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value = "/communityuserprofile", produces = "application/json")
public class CommunityUserProfileController {

    @Autowired
    CommunityUserProfileRepository communityUserProfileRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommunityRepository communityRepository;
    
    @Autowired
    CommunityController communityController;
    
    @Autowired
    UserController userController;

    @RequestMapping(value = "/trending", method = RequestMethod.GET)
    public ResponseEntity getTrending(@RequestParam Long num) {
        List<BigInteger> cids = communityUserProfileRepository.findTrendingCommunities(num);
        List<Community> communities = new ArrayList<>();
        for (BigInteger cid : cids) {
            communities.add(communityRepository.findCommunityByCid(cid.longValue()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(communities);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCommunityUserProfileUsername(@RequestParam String username, @RequestParam String commName) throws ExecutionException, InterruptedException {
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
    
    @RequestMapping(value = "/uid", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCommunityUserProfileById(@RequestParam long uid, @RequestParam long cid) {
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
    
    @RequestMapping(value = "/getcomms", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCommunityIds(@RequestParam long uid) {
        List<Long> communityIds = communityUserProfileRepository.findCommunityFromUser(uid);
        if(communityIds == null || communityIds.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        
        List<Community> comms = communityController.getCommunityProfiles(communityIds);
        
        if(comms == null || comms.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(comms);
    }
    
    @RequestMapping(value = "/getusers", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getUserIds(@RequestParam long cid) {
        List<Long> userIds = communityUserProfileRepository.findUserFromCommunity(cid);
        
        List<User> users = userController.getUserProfiles(userIds);
        
        return ResponseEntity.status(HttpStatus.OK).body(users);    }
    
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
    	ResponseEntity res = getCommunityUserProfileById(communityUserProfile.getUid(), communityUserProfile.getCid());
    	
    	if(res.getStatusCode() == HttpStatus.OK) {
    		CommunityUserProfile temp = (CommunityUserProfile) res.getBody();
    		communityUserProfileRepository.delete(temp);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    	}
    	
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find cm.user to delete");
    }
}
