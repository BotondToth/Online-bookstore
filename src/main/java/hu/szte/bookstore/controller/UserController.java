package hu.szte.bookstore.controller;

import hu.szte.bookstore.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Botond
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController extends BaseController {

    @GetMapping("/login")
    public ResponseEntity login(@RequestBody final UserDTO userDTO) {



        return new ResponseEntity(HttpStatus.OK);
    }



}
