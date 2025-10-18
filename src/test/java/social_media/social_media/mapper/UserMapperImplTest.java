package social_media.social_media.mapper;

import org.junit.jupiter.api.Test;
import social_media.social_media.dto.UserDto;
import social_media.social_media.model.UserModel;

class UserMapperImplTest {

    private final UserMapperImpl userMapper = new UserMapperImpl();

    @Test
    void fromUserModeltoUserDto() {
        // Arrange
        UserModel userModel = UserModel.builder().
                id(1L).
                fullName("testFullname").
                userName("testUserName").
                email("testEmail@email.com").
                bio("testBio").
                profilePicture("testProfilePicture").
                createdAt("2023-10-01").
                updatedAt("2023-10-01").
                build();

        // Act
        UserDto userDto = userMapper.fromUserModeltoUserDto(userModel);

        // Assert
        assert userDto != null;
        assert userDto.getId().equals(userModel.getId());
        assert userDto.getUserName().equals(userModel.getUserName());
    }


}
