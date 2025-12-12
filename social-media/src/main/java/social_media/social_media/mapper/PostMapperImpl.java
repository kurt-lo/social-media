package social_media.social_media.mapper;

import org.springframework.stereotype.Component;
import social_media.social_media.dto.PostDto;
import social_media.social_media.model.PostModel;

@Component
public class PostMapperImpl implements PostMapper{

    @Override
    public PostDto fromPostModelToPostDto(PostModel postModel) {
        if (postModel == null) return null;

        return PostDto.builder()
                .id(postModel.getId())
                .content(postModel.getContent())
                .imageUrl(postModel.getImageUrl())
                .userId(postModel.getUser().getId())
                .createdAt(postModel.getCreatedAt())
                .updatedAt(postModel.getUpdatedAt())
                .build();
    }

    @Override
    public PostModel fromPostDtoToPostModel(PostDto postDto) {
        if (postDto == null) return null;

        return PostModel.builder()
                .content(postDto.getContent())
                .imageUrl(postDto.getImageUrl())
                .build();
    }

}
