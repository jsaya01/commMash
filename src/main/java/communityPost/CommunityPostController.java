package communityPost;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/communityPost", produces = "application/json")
public class CommunityPostController {

	@Autowired
    CommunityPostRepository communityPostRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCommunityPostWithPID(@RequestParam long pid) {
    	Future<CommunityPost> communityPost = communityPostRepository.findCommunityPostByPID(pid);
    	
    	try {
    		communityPost.get().getPid();
            return ResponseEntity.status(HttpStatus.OK).body(communityPost.get());
    	}
    	catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not be found");
    	}
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity post(@RequestBody CommunityPost communityPost) {
    	communityPostRepository.save(communityPost);
    	
        return ResponseEntity.status(HttpStatus.OK).body("Posted");
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity put(@RequestBody CommunityPost communityPost) {
    	communityPostRepository.save(communityPost);
    	
        return ResponseEntity.status(HttpStatus.OK).body("Edited");
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody CommunityPost communityPost) {
    	ResponseEntity res = getCommunityPostWithPID(communityPost.getPid());
    	
    	if(res.getStatusCode() == HttpStatus.OK) {
    		CommunityPost temp = (CommunityPost) res.getBody();
    		communityPostRepository.deleteById(temp.getPid());
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    	}
    	
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find user to delete");
    }
}
