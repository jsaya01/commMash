package cm.matches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/matches", produces = "application/json")
public class MatchesController {

    @Autowired
    MatchesRepository matchesRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity post(@RequestBody Matches matches) {
        matchesRepository.save(matches);

        return ResponseEntity.status(HttpStatus.OK).body("Posted");
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity put(@RequestBody Matches matches) {
        matchesRepository.save(matches);

        return ResponseEntity.status(HttpStatus.OK).body("Edited");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody Matches matches) {
        matchesRepository.delete(matches);

        return  ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

}
