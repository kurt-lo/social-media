package social_media.social_media.mapper;

import org.springframework.stereotype.Component;
import social_media.social_media.dto.UpdateUserDto;
import social_media.social_media.dto.UserDto;
import social_media.social_media.dto.AuthRegistrationDto;
import social_media.social_media.enums.Role;
import social_media.social_media.model.UserModel;

@Component
public class UserMapperImpl implements UserMapper {

    // User Model to User DTO
    @Override
    public UserDto fromUserModeltoUserDto(UserModel userModel) {
        if (userModel == null) return null;

        return UserDto.builder()
                .id(userModel.getId())
                .fullName(userModel.getFullName())
                .userName(userModel.getUserName())
                .email(userModel.getEmail())
                .bio(userModel.getBio())
                .profilePicture(userModel.getProfilePicture())
                .role(userModel.getRole())
                .createdAt(userModel.getCreatedAt())
                .updatedAt(userModel.getUpdatedAt())
                .build();
    }

    // User DTO to Model
    @Override
    public UserModel toModel(UserDto userDto) {
        if (userDto == null) return null;

        return UserModel.builder()
                .id(userDto.getId())
                .fullName(userDto.getFullName())
                .userName(userDto.getUserName())
                .email(userDto.getEmail())
                .bio(userDto.getBio())
                .profilePicture(userDto.getProfilePicture())
                .createdAt(userDto.getCreatedAt())
                .updatedAt(userDto.getUpdatedAt())
                .build();
    }

    // Registration DTO to Model
    @Override
    public UserModel fromRegistrationDtoUserModel(AuthRegistrationDto registrationDto, Role role) {
        if (registrationDto == null) return null;

        return UserModel.builder()
                .fullName(registrationDto.getFullName())
                .userName(registrationDto.getUserName())
                .email(registrationDto.getEmail())
                .password(registrationDto.getPassword())
                .role(role)
                .build();
    }

    @Override
    public UserDto fromUserModelToUserDtoWithoutPassword(UserModel userModel) {
        if (userModel == null) return null;

        return UserDto.builder()
                .id(userModel.getId())
                .fullName(userModel.getFullName())
                .userName(userModel.getUserName())
                .email(userModel.getEmail())
                .bio(userModel.getBio())
                .profilePicture(userModel.getProfilePicture())
                .role(userModel.getRole())
                .createdAt(userModel.getCreatedAt())
                .updatedAt(userModel.getUpdatedAt())
                .build();
    }

    @Override
    public UpdateUserDto fromUpdatedUserModelToUserDto(UserModel updatedUserModel) {
        if (updatedUserModel == null) return null;

        return UpdateUserDto.builder()
                .id(updatedUserModel.getId())
                .fullName(updatedUserModel.getFullName())
                .userName(updatedUserModel.getUserName())
                .email(updatedUserModel.getEmail())
                .bio(updatedUserModel.getBio())
                .profilePicture(updatedUserModel.getProfilePicture())
                .role(updatedUserModel.getRole())
                .password(updatedUserModel.getPassword())
                .createdAt(updatedUserModel.getCreatedAt())
                .updatedAt(updatedUserModel.getUpdatedAt())
                .build();
    }
}
