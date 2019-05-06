package community_tags;

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
@RequestMapping(value = "/communityTags", produces = "application/json")
public class CommunityTagsController {

	@Autowired
    CommunityTagsRepository communityTagsRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCommunityTagsWithTID(@RequestParam long tid) {
    	Future<CommunityTags> communityTags = communityTagsRepository.findCommunityTagsByTID(tid);
    	
    	try {
    		communityTags.get().getTid();
            return ResponseEntity.status(HttpStatus.OK).body(communityTags.get());
    	}
    	catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not be found");
    	}
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity post(@RequestBody CommunityTags communityTags) {
    	communityTagsRepository.save(communityTags);
    	
        return ResponseEntity.status(HttpStatus.OK).body("Posted");
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity put(@RequestBody CommunityTags communityTags) {
    	communityTagsRepository.save(communityTags);
    	
        return ResponseEntity.status(HttpStatus.OK).body("Edited");
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody CommunityTags communityTags) {
    	ResponseEntity res = getCommunityTagsWithTID(communityTags.getTid());
    	
    	if(res.getStatusCode() == HttpStatus.OK) {
    		CommunityTags temp = (CommunityTags) res.getBody();
    		communityTagsRepository.deleteById(temp.getTid());
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    	}
    	
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find user to delete");
    }
}
