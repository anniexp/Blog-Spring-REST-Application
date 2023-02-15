package com.foodieblog.foodieblog.services;

import com.foodieblog.foodieblog.dtos.PostGetDto;
import com.foodieblog.foodieblog.dtos.PostPostDto;
import com.foodieblog.foodieblog.entities.Post;
import com.foodieblog.foodieblog.entities.User;
import com.foodieblog.foodieblog.exceptionHandlers.NotValidJsonBodyException;
import com.foodieblog.foodieblog.exceptionHandlers.PostNotFoundException;
import com.foodieblog.foodieblog.mapstruct.mappers.MapStructMapper;
import com.foodieblog.foodieblog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.foodieblog.foodieblog.validators.JsonRequestValidator.validateJsonInput;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final MapStructMapper mapStructMapper;
    private final UserService userService;

    @Autowired
    public PostService(PostRepository postRepository, MapStructMapper mapStructMapper, UserService userService) {
        this.postRepository = postRepository;
        this.mapStructMapper = mapStructMapper;
        this.userService = userService;
    }

    public void delete(PostGetDto postGetDto) {
        Post post = mapStructMapper.postGetDtoToPost(postGetDto);
        postRepository.delete(post);
    }

    public Post findByIdd(long id) throws PostNotFoundException {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found due to invalid post Id:" + id));
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    public PostGetDto getByIdd(int id) throws PostNotFoundException {
        Post post = postRepository.findById((long) id).orElseThrow(() -> new PostNotFoundException("Invalid post Id:" + id));
        PostGetDto postGetDto = mapStructMapper.postGetDto(post);
        User user = post.getAuthor();
        postGetDto.setAuthorUsernamee(user.getUsername());
        postGetDto.setNumberLikes(post.getPostLikes().size());


        return postGetDto;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<PostGetDto> getAllDtos() throws PostNotFoundException {
        List<Post> posts = findAll();
        List<PostGetDto> postGetDtos = mapStructMapper.getAllPostsDto(posts);
        for (int i = 0; i < posts.size(); i++) {
            int id = Math.toIntExact(posts.get(i).getPostId());
            PostGetDto getDto = getByIdd(id);
            postGetDtos.set(i, getDto);
        }
        return postGetDtos;
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public PostGetDto updatePost(long id, PostPostDto postPostDto, long authorId) throws NotValidJsonBodyException {
        try {
            if (authorId == 0) {
                throw new NotValidJsonBodyException("User is empty");
            }
            Post post = mapStructMapper.postPostDtoToPost(postPostDto);
            post.setAuthor(userService.findById(authorId).orElseThrow(() -> new NotValidJsonBodyException("User not found due to invalid user id: " + authorId)));

            Post oldPost = findByIdd(id);
            Post updatedPost = setPost(oldPost, post);
            PostGetDto body = mapStructMapper.postGetDto(updatedPost);
            body.setAuthorUsernamee(updatedPost.getAuthor().getUsername());
            return body;

        } catch (PostNotFoundException e) {
            throw new NotValidJsonBodyException(e.getMessage());
        }
    }

    public Post setPost(Post post, Post newPost) throws NotValidJsonBodyException {
        post.setDescription(newPost.getDescription());
        post.setText(newPost.getText());
        post.setAuthor(newPost.getAuthor());
        validateJsonInput(post);
        postRepository.save(post);

        return post;
    }

    public PostGetDto setPostGetDto(long id) throws PostNotFoundException {
        Post post = findByIdd(id);
        PostGetDto postGetDto = mapStructMapper.postGetDto(post);

        assert post != null;
        postGetDto.setAuthorUsernamee(post.getAuthor().getUsername());
        postGetDto.setNumberLikes(post.getPostLikes().size());

        return postGetDto;
    }

    public void create(PostPostDto postPostDto) throws NotValidJsonBodyException {
        Post newPost = mapStructMapper.postPostDtoToPost(postPostDto);
        int userId = postPostDto.getAuthorId();
        User author = userService.findById(userId).orElseThrow(() -> new NotValidJsonBodyException("User not found due to invalid user id: " + userId));
        newPost.setAuthor(author);

        validateJsonInput(newPost);
        userService.save(author);

        save(newPost);

    }

    public Optional<Post> findById(int postId) {
        return postRepository.findById((long) postId);
    }
}
