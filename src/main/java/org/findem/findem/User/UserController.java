package org.findem.findem.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/user", produces = "application/json")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Optional<User> get(@RequestParam String uid) {
    	return userRepository.getOne(uid);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void post(@RequestBody User user) {
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void put(@RequestBody User user) {
    }
}