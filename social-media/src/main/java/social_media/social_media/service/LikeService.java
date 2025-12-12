package social_media.social_media.service;

import org.springframework.stereotype.Service;
import social_media.social_media.dto.LikeDto;
import social_media.social_media.dto.ToggleLikeResponseDto;
import social_media.social_media.mapper.LikeMapper;
import social_media.social_media.model.LikeModel;
import social_media.social_media.model.PostModel;
import social_media.social_media.model.UserModel;
import social_media.social_media.repository.LikeRepository;
import social_media.social_media.repository.PostRepository;
import social_media.social_media.repository.UserRepository;

@Service
public class LikeService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;

    public LikeService(UserRepository userRepository,
                       PostRepository postRepository,
                       LikeRepository likeRepository,
                       LikeMapper likeMapper) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.likeMapper = likeMapper;
        this.likeRepository = likeRepository;
    }

    public LikeDto createLike(LikeDto likeDto) {
        UserModel user = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found from Like Service"));

        PostModel post = postRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found from Like Service"));

        boolean alreadyLiked = likeRepository.existsByUserAndPost(user, post);
        if (alreadyLiked) {
            throw new RuntimeException("User already liked this post");
        }

        LikeModel like = LikeModel.builder()
                .post(post)
                .user(user)
                .build();

        LikeModel savedLike = likeRepository.save(like);

        return likeMapper.fromLikeModelToLikeDto(savedLike);
    }

    public void unlikePost(LikeDto likeDto) {
        UserModel user = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found from Like Service"));

        PostModel post = postRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found from Like Service"));

        boolean likeExists = likeRepository.existsByUserAndPost(user, post);

        if (!likeExists) {
            throw new RuntimeException("You haven't like this post yet");
        }

        likeRepository.deleteByUserAndPost(user, post);
    }

    public ToggleLikeResponseDto toggleLike(Long userId, Long postId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found from Like Service"));

        PostModel post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found from Like Service"));

        boolean alreadyLiked = likeRepository.existsByUserAndPost(user, post);

        if (alreadyLiked) {
            likeRepository.deleteByUserAndPost(user, post);
            long newCount = likeRepository.countByPost(post);
            return ToggleLikeResponseDto.builder()
                    .liked(false)
                    .count(newCount)
                    .build();
        }

        LikeModel like = LikeModel.builder()
                .user(user)
                .post(post)
                .build();
        likeRepository.save(like);

        long newCount = likeRepository.countByPost(post);

        return ToggleLikeResponseDto.builder()
                .liked(true)
                .count(newCount)
                .build();
    }
}
