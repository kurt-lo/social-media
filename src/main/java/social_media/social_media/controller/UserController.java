package social_media.social_media.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social_media.social_media.dto.JwtResponseDto;
import social_media.social_media.dto.UserLoginDto;
import social_media.social_media.dto.UserRegistrationDto;
import social_media.social_media.model.UserModel;
import social_media.social_media.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, User!");
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        String response = userService.registerUser(userRegistrationDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> loginUser(@RequestBody UserLoginDto userLoginDto) {
        JwtResponseDto jwtResponseDto = userService.loginUser(userLoginDto);
        log.info("User logged in successfully: {}", userLoginDto.getEmail());
        return ResponseEntity.ok(jwtResponseDto);
    }
}
