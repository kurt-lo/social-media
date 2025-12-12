package social_media.social_media.mapper;

import org.springframework.stereotype.Component;
import social_media.social_media.dto.LikeDto;
import social_media.social_media.dto.ToggleLikeResponseDto;
import social_media.social_media.model.LikeModel;
import social_media.social_media.model.PostModel;
import social_media.social_media.model.UserModel;
import social_media.social_media.repository.PostRepository;
import social_media.social_media.repository.UserRepository;

@Component
public class LikeMapperImpl implements LikeMapper {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public LikeMapperImpl(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public LikeDto fromLikeModelToLikeDto(LikeModel likeModel) {
        if (likeModel == null) return null;

        return LikeDto.builder()
                .id(likeModel.getId())
                .userId(likeModel.getUser().getId())
                .postId(likeModel.getPost().getId())
                .createdAt(likeModel.getCreatedAt())
                .updatedAt(likeModel.getUpdatedAt())
                .build();
    }

    @Override
    public LikeModel fromLikeDtoToLikeModel(LikeDto likeDto) {
        if (likeDto == null) return null;

        UserModel user = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found."));

        PostModel post = postRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return LikeModel.builder()
                .id(likeDto.getId())
                .post(post)
                .user(user)
                .build();
    }

}
