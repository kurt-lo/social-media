package social_media.social_media.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social_media.social_media.dto.AuthLoginDto;
import social_media.social_media.dto.AuthRegistrationDto;
import social_media.social_media.dto.JwtResponseDto;
import social_media.social_media.enums.Role;
import social_media.social_media.model.UserModel;
import social_media.social_media.service.AuthService;
import social_media.social_media.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@Slf4j
public class AdminController {

    private final AuthService authService;
    private final UserService userService;

    public AdminController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AuthRegistrationDto authRegistrationDto) {
        String response = authService.registerUser(authRegistrationDto, Role.ADMIN);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> loginUser(@RequestBody AuthLoginDto authLoginDto) {
        JwtResponseDto jwtResponseDto = authService.loginUser(authLoginDto, Role.ADMIN);
        log.info("User logged in successfully: {}", authLoginDto.getEmail());
        return new ResponseEntity<>(jwtResponseDto, HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<List<UserModel>> getAllUsers() {
//        List<UserModel> users = userService.getAllUsers();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
}
