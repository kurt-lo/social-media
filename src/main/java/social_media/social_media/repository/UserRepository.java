package social_media.social_media.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import social_media.social_media.model.UserModel;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    Optional<UserModel> findByEmail(String email);

}
