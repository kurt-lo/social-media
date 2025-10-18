package social_media.social_media.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import social_media.social_media.model.UserModel;
import social_media.social_media.repository.UserRepository;

import java.util.List;

@Service
@Transactional
@Slf4j
//@RequiredArgsConstructor // used to generate a constructor with required arguments
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;;
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }
}
