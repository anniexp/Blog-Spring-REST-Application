package com.foodieblog.foodieblog.mapstruct.mappers;

import com.foodieblog.foodieblog.dtos.*;
import com.foodieblog.foodieblog.entities.*;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    PostSlimDto postSlimDto(Post post);
    PostGetDto postGetDto(Post post);
    PostPostDto postPostDto(Post post);
    List<PostGetDto> getAllPostsDto(List<Post> posts);
    Post postPostDtoToPost(PostPostDto postPostDto);
    Post postGetDtoToPost(PostGetDto postGetDto);

    AuthorGetAllDto authorGetDto(User user);
    UserGetDto userGetDto(User user);
    UserPostDto userPostDto(User user);
    List<AuthorGetAllDto> authorsToAuthorAllDtos(List<User> authors);
    User userPostDtoToUser(UserPostDto userPostDto);
    User userGetDtoToUser(UserGetDto userGetDto);

    CommentPostDto commentPostDto(Comment comment);
    Comment commentDtoToComment(CommentGetAllDto commentGetAllDto);
    Comment commentPostDtoToComment(CommentPostDto commentPostDto);
    Comment commentSlimDtoToComment(CommentSlimDto commentSlimDto);

    CommentGetAllDto commentGetAllDto(Comment comment);
    List<CommentGetAllDto> commentGetAllDto(List<Comment> comments);
    CommentSlimDto commentSlimDto(Comment comment);


    RoleGetDto roleGetDto(Role role);
    Role roleDtoToRole(RoleGetDto roleGetDto);
    List<RoleGetDto> roleGetAllDto(List<Role> roles);

    LikeDto likeDto(Like like);
    Like likeDtoToLike(LikeDto likeDto);
    List<LikeDto> allLikeDtos(List<Like> likes);
    LikePostDto likePostDto(Like like);
    Like likePostDtoToLike(LikePostDto likePostDto);
}
