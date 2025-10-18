package social_media.social_media.mapper;

import social_media.social_media.dto.UpdateUserDto;
import social_media.social_media.dto.UserDto;
import social_media.social_media.dto.AuthRegistrationDto;
import social_media.social_media.enums.Role;
import social_media.social_media.model.UserModel;

public interface UserMapper {
    UserDto fromUserModeltoUserDto(UserModel userModel);
    UserModel toModel(UserDto userDto);
    UserModel fromRegistrationDtoUserModel(AuthRegistrationDto authRegistrationDto, Role role);
    UserDto fromUserModelToUserDtoWithoutPassword(UserModel userModel);
    UpdateUserDto fromUpdatedUserModelToUserDto(UserModel updatedUserModel);
}
