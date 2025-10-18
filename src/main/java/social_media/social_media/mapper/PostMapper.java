package social_media.social_media.mapper;

import social_media.social_media.dto.PostDto;
import social_media.social_media.model.PostModel;

public interface PostMapper {
    PostDto fromPostModelToPostDto(PostModel postModel);
    PostModel fromPostDtoToPostModel(PostDto postDto);
}
