package social_media.social_media.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String fullName;
    private String userName;
    private String email;
    private String bio;
    private String profilePicture;
    private String createdAt;
    private String updatedAt;
}
