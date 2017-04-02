package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.model.ResultMessage;
import user.model.User;
import user.model.UserInformation;
import user.model.UserLogin;
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
        ResultMessage resultMessage = new ResultMessage(user.getUsername(), "Successfully created");
        this.userRepository.createUser(user);
        return new ResponseEntity(resultMessage, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public UserInformation getUser(@PathVariable String username) {
        return this.userRepository.getUser(username);
    }

    @RequestMapping(value = "/users/{username}/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable String username) {
        ResultMessage resultMessage = new ResultMessage(username, "Successfully Deleted");
        this.userRepository.deleteUser(username);
        return new ResponseEntity(resultMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/authentication", method = RequestMethod.POST)
    public ResponseEntity authenticateUser(@RequestBody UserLogin login) {
        String token = userRepository.authenticate(login);
        return new ResponseEntity(token, HttpStatus.CREATED);
    }

}
