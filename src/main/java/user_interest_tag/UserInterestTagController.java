package user_interest_tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/interest", produces = "application/json")
public class UserInterestTagController {

    @Autowired
    UserInterestTagRepository userInterestTagRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity post(@RequestBody UserInterestTag userInterestTag) {
        userInterestTagRepository.save(userInterestTag);

        return ResponseEntity.status(HttpStatus.OK).body("Posted");
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity put(@RequestBody UserInterestTag userInterestTag) {
        userInterestTagRepository.save(userInterestTag);
        return ResponseEntity.status(HttpStatus.OK).body("Edited");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody UserInterestTag userInterestTag) {
        userInterestTagRepository.delete(userInterestTag);

        return  ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

}
