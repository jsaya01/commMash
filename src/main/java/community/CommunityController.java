package community;

import java.util.List;
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
@RequestMapping(value = "/community", produces = "application/json")
public class CommunityController {

    @Autowired
    CommunityRepository communityRepository;
    
    public List<Community> getCommunityProfiles(List<Long> cids){
    	List <Community> comms = null;
    	for(Long cid: cids) {
    		Community comm = communityRepository.findById(cid).get();
    		comms.add(comm);
    	}
    	return comms;
    }
    
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCommunityWithName(@RequestParam String name) {
    	Future<Community> community = communityRepository.findCommunityByName(name);
    	
    	try {
    		community.get().getName();
            return ResponseEntity.status(HttpStatus.OK).body(community.get());
    	}
    	catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not be found");
    	}
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity post(@RequestBody Community community) {
    	communityRepository.save(community);
    	
        return ResponseEntity.status(HttpStatus.OK).body("Posted");
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity put(@RequestBody Community community) {
    	communityRepository.save(community);
    	
        return ResponseEntity.status(HttpStatus.OK).body("Edited");
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody Community community) {
    	ResponseEntity res = getCommunityWithName(community.getName());
    	
    	if(res.getStatusCode() == HttpStatus.OK) {
    		Community temp = (Community) res.getBody();
    		communityRepository.deleteById(temp.getCid());
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    	}
    	
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find user to delete");
    }
}
