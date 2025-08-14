package social_media.social_media.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import social_media.social_media.model.UserModel;
import social_media.social_media.repository.UserRepository;
import social_media.social_media.security.CustomerUserDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomerUserDetailService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserModel> userModel = userRepository.findByEmail(email);
        if (userModel.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found with username: " + email);
        }
        return new org.springframework.security.core.userdetails.User(
                userModel.get().getEmail(),
                userModel.get().getPassword(),
                Collections.emptyList()
        );
    }

}
