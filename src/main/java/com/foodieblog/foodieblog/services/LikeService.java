package com.foodieblog.foodieblog.services;

import com.foodieblog.foodieblog.dtos.LikeDto;
import com.foodieblog.foodieblog.dtos.LikePostDto;
import com.foodieblog.foodieblog.entities.Like;
import com.foodieblog.foodieblog.entities.Post;
import com.foodieblog.foodieblog.entities.User;
import com.foodieblog.foodieblog.exceptionHandlers.LikeNotFoundException;
import com.foodieblog.foodieblog.exceptionHandlers.NotValidJsonBodyException;
import com.foodieblog.foodieblog.mapstruct.mappers.MapStructMapper;
import com.foodieblog.foodieblog.repositories.LikeRepository;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.foodieblog.foodieblog.validators.JsonRequestValidator.validateJsonInput;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final MapStructMapper mapStructMapper;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public LikeService(LikeRepository likeRepository, MapStructMapper mapStructMapper, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.mapStructMapper = mapStructMapper;
        this.userService = userService;
        this.postService = postService;
    }

    public LikeDto getByIdd(int id) throws LikeNotFoundException {
        Like like = likeRepository.findById((long) id).orElseThrow(() -> new LikeNotFoundException("Like not found due to invalid like id:" + id));
        LikeDto likeDto = mapStructMapper.likeDto(like);

        User user = like.getLikingUser();
        likeDto.setUserId(Math.toIntExact(user.getUserId()));
        Post post = like.getLikedPost();
        likeDto.setPostId(Math.toIntExact(post.getPostId()));

        return likeDto;
    }

    public void create(LikePostDto likePostDto) throws NotValidJsonBodyException {
        validateJsonInput(likePostDto);
        Like newLike = mapStructMapper.likePostDtoToLike(likePostDto);

        int userId = likePostDto.getUserId();
        User author = userService.findById(userId).orElseThrow(() -> new NotValidJsonBodyException("User not found due to invalid id: " + userId));
        int postId = likePostDto.getPostId();
        Post post = postService.findById(postId).orElseThrow(() -> new NotValidJsonBodyException("Post is not found due to invalid postId: " + postId));;

        boolean alreadyExist = !isLikePossible(author, post);
        if (alreadyExist) {
            throw new NotValidJsonBodyException("User has already liked this post!");
        }
        newLike.setLikingUser(author);
        newLike.setLikedPost(post);

        userService.save(author);
        postService.save(post);
        likeRepository.save(newLike);

    }

    public boolean isLikePossible(User user, Post post) {
        List<Like> likes = likeRepository.findAll();

        for (Like like : likes) {
            if (Objects.equals(like.getLikedPost().getPostId(), post.getPostId()) &&
                    Objects.equals(like.getLikingUser().getUserId(), user.getUserId())) {
                return false;
            }
        }

        return true;
    }
}
