package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.jwt.JwtService;
import user.model.*;
import user.repository.UserRepository;

/**
 * Created by Adisorn on 2/4/2560.
 */
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;

    @Value("${authentication.token.header}")
    String tokenHeader;

    @Autowired
    private JwtService jwtService;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody User user) {
        ResultMessage resultMessage = new ResultMessage(user.getUsername(), "Successfully Created");
        this.userRepository.createUser(user);
        return new ResponseEntity(resultMessage, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public User getUser(@PathVariable String username) {
        return this.userRepository.getUser(username);
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable String username) {
        ResultMessage resultMessage = new ResultMessage(username, "Successfully Deleted");
        this.userRepository.deleteUser(username);
        return new ResponseEntity(resultMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@PathVariable String username, @RequestBody User user) {
        this.userRepository.updateUser(username, user);
        ResultMessage resultMessage = new ResultMessage(user.getUsername(), "Successfully Updated");
        return new ResponseEntity(resultMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/authentication", method = RequestMethod.POST)
    public ResponseEntity authenticateUser(@RequestBody UserLogin login) {
        String token = userRepository.authenticate(login);
        return new ResponseEntity(token, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/validate", method = RequestMethod.GET)
    public ResponseEntity validateToken(@RequestParam(value = "token", required = true) String token) {
        JwtUser user = userRepository.validateToken(token);
        return new ResponseEntity(user, HttpStatus.OK);
    }

}
