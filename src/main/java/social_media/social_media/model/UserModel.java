package social_media.social_media.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(unique = true, nullable = false)
    private String userName;
    private String email;
    private String password;
    private String bio;
    private String profilePicture;
    private String createdAt;
    private String updatedAt;

}
