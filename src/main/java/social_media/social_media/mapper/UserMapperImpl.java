package social_media.social_media.mapper;

import org.springframework.stereotype.Component;
import social_media.social_media.dto.UserDto;
import social_media.social_media.dto.UserRegistrationDto;
import social_media.social_media.model.UserModel;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(UserModel userModel) {
        if (userModel == null) return null;

        return UserDto.builder()
                .id(userModel.getId())
                .fullName(userModel.getFullName())
                .userName(userModel.getUserName())
                .email(userModel.getEmail())
                .bio(userModel.getBio())
                .profilePicture(userModel.getProfilePicture())
                .createdAt(userModel.getCreatedAt())
                .updatedAt(userModel.getUpdatedAt())
                .build();
    }

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

    @Override
    public UserModel fromRegistrationDto(UserRegistrationDto registrationDto) {
        if (registrationDto == null) return null;

        return UserModel.builder()
                .fullName(registrationDto.getFullName())
                .userName(registrationDto.getUserName())
                .email(registrationDto.getEmail())
                .password(registrationDto.getPassword())
                .build();
    }
}
