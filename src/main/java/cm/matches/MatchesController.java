package cm.matches;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cm.user.User;
import cm.user.UserController;

@RestController
@RequestMapping(value = "/matches", produces = "application/json")
public class MatchesController {

    @Autowired
    MatchesRepository matchesRepository;
    
    @Autowired
    UserController userController;
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getMatchesForUser(@RequestParam long uid) {
        List<Object[]> matchObs = matchesRepository.findMatchesFromUid1(uid);
        matchObs.addAll(matchesRepository.findMatchesFromUid2(uid));
        List<Long> userids = new ArrayList<>();
        
        for(Object o[] : matchObs) {
        	userids.add((long)(o[0]));
        }
        
        List<User> users = userController.getUserProfiles(userids);
        
        ArrayList<HashMap<String, Object>> customMatchObjs = new ArrayList<HashMap<String, Object>>();
        for(int x = 0; x<users.size(); x++) {
        	HashMap<String, Object> match = new HashMap<>();
        	match.put("uid", users.get(x).getUid());
        	match.put("fname", users.get(x).getFname());
        	match.put("dt", matchObs.get(x)[1].toString());
        	match.put("imagepath", users.get(x).getImagepath());
        	match.put("description", users.get(x).getDescription());

        	customMatchObjs.add(match);
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(customMatchObjs);    
    }

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
