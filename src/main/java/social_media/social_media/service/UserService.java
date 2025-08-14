package social_media.social_media.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import social_media.social_media.dto.JwtResponseDto;
import social_media.social_media.dto.UserLoginDto;
import social_media.social_media.dto.UserRegistrationDto;
import social_media.social_media.jwt.JwtUtil;
import social_media.social_media.mapper.UserMapper;
import social_media.social_media.model.UserModel;
import social_media.social_media.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
//@RequiredArgsConstructor // used to generate a constructor with required arguments
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, UserMapper userMapper,
                       JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public String registerUser(UserRegistrationDto userRegistrationDto) {
        if (userRepository.existsByUserName(userRegistrationDto.getUserName())) {
            log.error("User registration failed: Username already exists.");
            return "Username already exists!";
        }

        if (userRepository.existsByEmail(userRegistrationDto.getEmail())) {
            log.error("User registration failed: Email already exists.");
            return "Email already exists!";
        }

        UserModel user = userMapper.fromRegistrationDto(userRegistrationDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("User registered successfully: {}", user.getUserName());
        return "User created successfully!";
    }

    public JwtResponseDto loginUser(UserLoginDto userLoginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword()));

            String token = jwtUtil.generateToken(authentication.getName());
            return new JwtResponseDto(token);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }
}
