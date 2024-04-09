package com.example.blogapp.Contrroller;

import com.example.blogapp.Entity.Register;
import com.example.blogapp.Entity.User;
import com.example.blogapp.Payload.LoginDTO;
import com.example.blogapp.Payload.LoginMessage;
import com.example.blogapp.Payload.RegisterDto;
import com.example.blogapp.Service.RegisterService;
import com.example.blogapp.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RegisterService registerService;

    @PostMapping("/save")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createuser = userService.createuser(user);
        return new ResponseEntity<>(createuser, HttpStatus.CREATED);

    }

    @GetMapping("/get")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<User>>getAllUsrs(){
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }
    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String>createUsers(@RequestBody RegisterDto userDto){
        String message = registerService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"" + message + "\"}");
    }
//    @PostMapping("/login")
//    @CrossOrigin(origins = "http://localhost:4200")
//    public ResponseEntity<LoginMessage>loginVerify(@RequestBody LoginDTO loginDTO){
//        LoginMessage loginMessage = registerService.loginVerify(loginDTO);
//        return new ResponseEntity<>(loginMessage,HttpStatus.OK);
//    }
@PostMapping("/login")
public ResponseEntity<LoginMessage> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
    LoginMessage loginMessage = registerService.loginVerify(loginDTO);
    if (loginMessage.getStatus()) {
        Register user = registerService.findByEmail(loginDTO.getEmail());
        session.setAttribute("user", user);
    }
    return new ResponseEntity<>(loginMessage, HttpStatus.OK);
}

    @GetMapping("/logout")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> logoutUser(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }
}
