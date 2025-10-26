package social_media.social_media.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social_media.social_media.dto.PostDto;
import social_media.social_media.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Long userId) {
        PostDto post = postService.createPost(postDto, userId);
        return ResponseEntity.status(201).body(post); // another way to set status and body
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {
        PostDto post = postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getAllPostsByUserId(@PathVariable Long userId) {
        List<PostDto> posts = postService.getAllPostsByUserId(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PatchMapping("/{postId}/user/{userId}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto updatedPostDto,
                                                  @PathVariable Long postId,
                                                  @PathVariable Long userId) {
        PostDto updatedPost = postService.updatePostById(updatedPostDto, postId, userId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/{postId})")
    public ResponseEntity<Void> deletePostById(@PathVariable Long postId) {
        postService.deletePostById(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
