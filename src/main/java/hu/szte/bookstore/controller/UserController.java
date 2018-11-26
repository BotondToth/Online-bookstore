package hu.szte.bookstore.controller;

import hu.szte.bookstore.dto.RegistrationDTO;
import hu.szte.bookstore.dto.UserDTO;
import hu.szte.bookstore.model.User;
import hu.szte.bookstore.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Botond
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestBody final UserDTO userDTO) {
        final User user = userService.getUserByEmail(userDTO.getEmail());
        if (user == null) {
            return "";
        }
        if (!user.getEmail().equals(userDTO.getEmail())) {
            return "";
        }
        if (!user.getPassw().equals(userDTO.getPassword())) {
            return "";
        }
        return "ok";
    }

    @PostMapping("/register")
    public User register(@RequestBody RegistrationDTO registrationDTO) {
        final User user = new User(registrationDTO.getEmail(),registrationDTO.getFirstName(), registrationDTO.getLastName(), registrationDTO.getPassword());
        return userService.register(user);
    }

}
