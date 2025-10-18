package social_media.social_media.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import social_media.social_media.dto.JwtResponseDto;
import social_media.social_media.dto.AuthLoginDto;
import social_media.social_media.dto.AuthRegistrationDto;
import social_media.social_media.enums.Role;
import social_media.social_media.jwt.JwtUtil;
import social_media.social_media.mapper.UserMapper;
import social_media.social_media.model.UserModel;
import social_media.social_media.repository.UserRepository;

@Service
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(
                       UserRepository userRepository,
                       UserMapper userMapper,
                       BCryptPasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtUtil jwtUtil)
    {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public String registerUser(AuthRegistrationDto authRegistrationDto, Role role) {
        if (userRepository.existsByUserName(authRegistrationDto.getUserName())) {
            log.error("User registration failed: Username already exists.");
            throw new RuntimeException("Username already exists!");
        }

        if (userRepository.existsByEmail(authRegistrationDto.getEmail())) {
            log.error("User registration failed: Email already exists.");
            return "Email already exists!";
        }

        UserModel user = userMapper.fromRegistrationDtoUserModel(authRegistrationDto, role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
        userRepository.save(user);

        log.info("User registered successfully: {}", user.getUserName());
        return "User created successfully!";
    }

    public JwtResponseDto loginUser(AuthLoginDto authLoginDto, Role expectedRole) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authLoginDto.getEmail(),
                            authLoginDto.getPassword())
            );

            String email = authentication.getName();

            UserModel userModel = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

            if (!passwordEncoder.matches(authLoginDto.getPassword(), userModel.getPassword())) {
                throw new RuntimeException("Invalid password");
            }

            if (!userModel.getRole().equals(expectedRole)) {
                throw new RuntimeException("Unauthorized role");
            }

            String token = jwtUtil.generateToken(
                    email,
                    userModel.getRole().name(),
                    null
            );

            return new JwtResponseDto(email, token);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid email or password", e);
        }
    }

}
