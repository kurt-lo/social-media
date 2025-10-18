package social_media.social_media.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

    private Long id;
    private String content;
    private String imageUrl;
    private Long userId;
    private String createdAt;
    private String updatedAt;
}

