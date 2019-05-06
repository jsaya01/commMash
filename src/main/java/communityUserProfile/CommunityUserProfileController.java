package communityUserProfile;

import community.Community;
import community.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.User;
import user.UserRepository;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value = "/commUserProf", produces = "application/json")
public class CommunityUserProfileController {

    @Autowired
    CommunityUserProfileRepository communityUserProfileRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommunityRepository communityRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCommUserProf(@RequestParam String username, @RequestParam String commName) throws ExecutionException, InterruptedException {
        Future<User> user = userRepository.findUserByUsername(username);

        Future<Community> community = communityRepository.findCommunityByName(commName);

        Future<CommunityUserProfile> communityUserProfile = communityUserProfileRepository.findCommunityUserProfile(user.get().getUid(),
                community.get().getCid());

        try{
            communityUserProfile.get().getDescription();
            return ResponseEntity.status(HttpStatus.OK).body(communityUserProfile.get());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not be found");
        }

    }

}
