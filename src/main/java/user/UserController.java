package user;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Future;

@RestController
@RequestMapping(value = "/user", produces = "application/json")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getUserWithUsername(@RequestParam String username) {
    	Future<User> user = userRepository.findUserByUsername(username);
    	
    	try {
    		user.get().getUsername();
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
    	}
    	catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not be found");
    	}
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity post(@RequestBody User user) {
    	userRepository.save(user);
    	
        return ResponseEntity.status(HttpStatus.OK).body("Posted");
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity put(@RequestBody User user) {
    	userRepository.save(user);
    	
        return ResponseEntity.status(HttpStatus.OK).body("Edited");
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody User user) {
    	ResponseEntity res = getUserWithUsername(user.getUsername());
    	
    	if(res.getStatusCode() == HttpStatus.OK) {
    		User temp = (User) res.getBody();
    		userRepository.deleteById(temp.getUid());
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    	}
    	
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find user to delete");
    }
}