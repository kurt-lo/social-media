package social_media.social_media.service;

import org.springframework.stereotype.Service;
import social_media.social_media.dto.PostDto;
import social_media.social_media.mapper.PostMapper;
import social_media.social_media.model.PostModel;
import social_media.social_media.model.UserModel;
import social_media.social_media.repository.PostRepository;
import social_media.social_media.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository,
                       UserRepository userRepository,
                       PostMapper postMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postMapper = postMapper;
    }

    public PostDto createPost(PostDto postDto, Long id) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found ID: " + id));

        PostModel post = postMapper.fromPostDtoToPostModel(postDto);

        post.setUserModel(user);

        PostModel savedPost = postRepository.save(post);
        return postMapper.fromPostModelToPostDto(savedPost);
    }

    public PostDto getPostById(Long postId) {
        PostModel post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post to get not found ID: " + postId));

        return postMapper.fromPostModelToPostDto(post);
    }

    public List<PostDto> getAllPostsByUserId(Long userId) {
        List<PostModel> posts = postRepository.findByUserModelId(userId);

        return posts.stream()
                .map(postMapper::fromPostModelToPostDto)
                .collect(Collectors.toList());

    }

    public PostDto updatePostById(PostDto updatedPostDto, Long postId, Long userId) {
        PostModel existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found ID: " + postId));

        if (!existingPost.getUserModel().getId().equals(userId)) {
            throw new RuntimeException("User ID: " + userId + " is not authorized to update this post.");
        }

        if (updatedPostDto.getContent() != null) {
            existingPost.setContent(updatedPostDto.getContent());
        }

        if (updatedPostDto.getImageUrl() != null) {
            existingPost.setImageUrl(updatedPostDto.getImageUrl());
        }

        PostModel updatedPost = postRepository.save(existingPost);
        return postMapper.fromPostModelToPostDto(updatedPost);
    }

    public void deletePostById(Long postId) {
        PostModel post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post to delete not found ID: " + postId));
        postRepository.delete(post);
    }
}
