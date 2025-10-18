package social_media.social_media.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social_media.social_media.dto.*;
import social_media.social_media.enums.Role;
import social_media.social_media.mapper.UserMapper;
import social_media.social_media.model.UserModel;
import social_media.social_media.service.AuthService;
import social_media.social_media.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    private final AuthService authService;
    private final UserService userService;

    public UserController(AuthService authService,
                          UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, User!");
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AuthRegistrationDto authRegistrationDto) {
        String response = authService.registerUser(authRegistrationDto, Role.USER);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> loginUser(@RequestBody AuthLoginDto authLoginDto) {
        JwtResponseDto jwtResponseDto = authService.loginUser(authLoginDto, Role.USER);
        log.info("User logged in successfully: {}", authLoginDto.getEmail());
        return new ResponseEntity<>(jwtResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateUserDto> updateProfile(@PathVariable Long id, @RequestBody UserModel updatedUser) {
        UpdateUserDto updatedUserDto = userService.updateProfile(id, updatedUser);
        log.info("User profile updated successfully for user id: {}", id);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

}
