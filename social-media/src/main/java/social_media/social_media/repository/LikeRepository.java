package social_media.social_media.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import social_media.social_media.model.LikeModel;
import social_media.social_media.model.PostModel;
import social_media.social_media.model.UserModel;

@Repository
public interface LikeRepository extends JpaRepository<LikeModel, Long> {
    boolean existsByUserAndPost(UserModel user, PostModel post);
    void deleteByUserAndPost(UserModel user, PostModel post);
    long countByPost(PostModel post);
}
