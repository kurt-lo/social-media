package social_media.social_media.mapper;

import social_media.social_media.dto.UserDto;
import social_media.social_media.model.UserModel;

public interface UserMapper {
    UserDto toDto(UserModel userModel);
    UserModel toModel(UserDto userDto);
}
