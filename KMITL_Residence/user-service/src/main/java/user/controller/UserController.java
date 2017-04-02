package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.model.ResultMessage;
import user.model.User;
import user.model.UserInformation;
import user.repository.UserRepository;

/**
 * Created by Adisorn on 2/4/2560.
 */
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody User user) {
        ResultMessage resultMessage = new ResultMessage(user.getUsername(), "Create Success");
        this.userRepository.createUser(user);
        return new ResponseEntity(resultMessage, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public UserInformation getUser(@PathVariable String username) {
        return this.userRepository.getUser(username);
    }

}
