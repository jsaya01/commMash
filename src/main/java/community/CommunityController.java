package community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCommunityWithName(@RequestParam String name) {
    	return null;
    }
}
