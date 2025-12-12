package social_media.social_media.mapper;

import social_media.social_media.dto.LikeDto;
import social_media.social_media.model.LikeModel;

public interface LikeMapper {
    LikeDto fromLikeModelToLikeDto(LikeModel likeModel);
    LikeModel fromLikeDtoToLikeModel(LikeDto likeDto);
}
