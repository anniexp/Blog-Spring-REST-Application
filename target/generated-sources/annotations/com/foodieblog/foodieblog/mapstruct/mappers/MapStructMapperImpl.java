package com.foodieblog.foodieblog.mapstruct.mappers;

import com.foodieblog.foodieblog.dtos.AuthorGetAllDto;
import com.foodieblog.foodieblog.dtos.CommentGetAllDto;
import com.foodieblog.foodieblog.dtos.CommentPostDto;
import com.foodieblog.foodieblog.dtos.CommentSlimDto;
import com.foodieblog.foodieblog.dtos.LikeDto;
import com.foodieblog.foodieblog.dtos.LikePostDto;
import com.foodieblog.foodieblog.dtos.PostGetDto;
import com.foodieblog.foodieblog.dtos.PostPostDto;
import com.foodieblog.foodieblog.dtos.PostSlimDto;
import com.foodieblog.foodieblog.dtos.RoleGetDto;
import com.foodieblog.foodieblog.dtos.UserGetDto;
import com.foodieblog.foodieblog.dtos.UserPostDto;
import com.foodieblog.foodieblog.entities.Comment;
import com.foodieblog.foodieblog.entities.Like;
import com.foodieblog.foodieblog.entities.Post;
import com.foodieblog.foodieblog.entities.Role;
import com.foodieblog.foodieblog.entities.User;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-15T13:54:44+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public PostSlimDto postSlimDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostSlimDto postSlimDto = new PostSlimDto();

        if ( post.getPostId() != null ) {
            postSlimDto.setPostId( post.getPostId().intValue() );
        }
        postSlimDto.setDescription( post.getDescription() );
        postSlimDto.setCreatedAt( post.getCreatedAt() );

        return postSlimDto;
    }

    @Override
    public PostGetDto postGetDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostGetDto postGetDto = new PostGetDto();

        if ( post.getPostId() != null ) {
            postGetDto.setPostId( post.getPostId().intValue() );
        }
        postGetDto.setDescription( post.getDescription() );
        postGetDto.setCreatedAt( post.getCreatedAt() );
        postGetDto.setText( post.getText() );

        return postGetDto;
    }

    @Override
    public PostPostDto postPostDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostPostDto postPostDto = new PostPostDto();

        postPostDto.setDescription( post.getDescription() );
        postPostDto.setText( post.getText() );

        return postPostDto;
    }

    @Override
    public List<PostGetDto> getAllPostsDto(List<Post> posts) {
        if ( posts == null ) {
            return null;
        }

        List<PostGetDto> list = new ArrayList<PostGetDto>( posts.size() );
        for ( Post post : posts ) {
            list.add( postGetDto( post ) );
        }

        return list;
    }

    @Override
    public Post postPostDtoToPost(PostPostDto postPostDto) {
        if ( postPostDto == null ) {
            return null;
        }

        Post post = new Post();

        post.setDescription( postPostDto.getDescription() );
        post.setText( postPostDto.getText() );

        return post;
    }

    @Override
    public Post postGetDtoToPost(PostGetDto postGetDto) {
        if ( postGetDto == null ) {
            return null;
        }

        Post post = new Post();

        post.setPostId( (long) postGetDto.getPostId() );
        post.setDescription( postGetDto.getDescription() );
        post.setText( postGetDto.getText() );
        post.setCreatedAt( postGetDto.getCreatedAt() );

        return post;
    }

    @Override
    public AuthorGetAllDto authorGetDto(User user) {
        if ( user == null ) {
            return null;
        }

        AuthorGetAllDto authorGetAllDto = new AuthorGetAllDto();

        authorGetAllDto.setUserId( user.getUserId() );
        authorGetAllDto.setUsername( user.getUsername() );
        authorGetAllDto.setPosts( postSetToPostSlimDtoSet( user.getPosts() ) );

        return authorGetAllDto;
    }

    @Override
    public UserGetDto userGetDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserGetDto userGetDto = new UserGetDto();

        userGetDto.setUserId( user.getUserId() );
        userGetDto.setUsername( user.getUsername() );
        userGetDto.setEnabled( user.isEnabled() );

        return userGetDto;
    }

    @Override
    public UserPostDto userPostDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserPostDto userPostDto = new UserPostDto();

        userPostDto.setPassword( user.getPassword() );
        userPostDto.setUsername( user.getUsername() );
        userPostDto.setEnabled( user.isEnabled() );

        return userPostDto;
    }

    @Override
    public List<AuthorGetAllDto> authorsToAuthorAllDtos(List<User> authors) {
        if ( authors == null ) {
            return null;
        }

        List<AuthorGetAllDto> list = new ArrayList<AuthorGetAllDto>( authors.size() );
        for ( User user : authors ) {
            list.add( authorGetDto( user ) );
        }

        return list;
    }

    @Override
    public User userPostDtoToUser(UserPostDto userPostDto) {
        if ( userPostDto == null ) {
            return null;
        }

        User user = new User();

        user.setPassword( userPostDto.getPassword() );
        user.setUsername( userPostDto.getUsername() );
        user.setEnabled( userPostDto.isEnabled() );

        return user;
    }

    @Override
    public User userGetDtoToUser(UserGetDto userGetDto) {
        if ( userGetDto == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( userGetDto.getUsername() );
        user.setEnabled( userGetDto.isEnabled() );
        user.setUserId( userGetDto.getUserId() );

        return user;
    }

    @Override
    public CommentPostDto commentPostDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentPostDto commentPostDto = new CommentPostDto();

        commentPostDto.setText( comment.getText() );

        return commentPostDto;
    }

    @Override
    public Comment commentDtoToComment(CommentGetAllDto commentGetAllDto) {
        if ( commentGetAllDto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setCommentId( commentGetAllDto.getCommentId() );
        comment.setText( commentGetAllDto.getText() );

        return comment;
    }

    @Override
    public Comment commentPostDtoToComment(CommentPostDto commentPostDto) {
        if ( commentPostDto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setText( commentPostDto.getText() );

        return comment;
    }

    @Override
    public Comment commentSlimDtoToComment(CommentSlimDto commentSlimDto) {
        if ( commentSlimDto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setText( commentSlimDto.getText() );

        return comment;
    }

    @Override
    public CommentGetAllDto commentGetAllDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentGetAllDto commentGetAllDto = new CommentGetAllDto();

        commentGetAllDto.setCommentId( comment.getCommentId() );
        commentGetAllDto.setText( comment.getText() );

        return commentGetAllDto;
    }

    @Override
    public List<CommentGetAllDto> commentGetAllDto(List<Comment> comments) {
        if ( comments == null ) {
            return null;
        }

        List<CommentGetAllDto> list = new ArrayList<CommentGetAllDto>( comments.size() );
        for ( Comment comment : comments ) {
            list.add( commentGetAllDto( comment ) );
        }

        return list;
    }

    @Override
    public CommentSlimDto commentSlimDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentSlimDto commentSlimDto = new CommentSlimDto();

        commentSlimDto.setText( comment.getText() );

        return commentSlimDto;
    }

    @Override
    public RoleGetDto roleGetDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleGetDto roleGetDto = new RoleGetDto();

        if ( role.getRoleId() != null ) {
            roleGetDto.setRoleId( role.getRoleId().intValue() );
        }
        roleGetDto.setRoleName( role.getRoleName() );
        roleGetDto.setUsers( userSetToUserGetDtoSet( role.getUsers() ) );

        return roleGetDto;
    }

    @Override
    public Role roleDtoToRole(RoleGetDto roleGetDto) {
        if ( roleGetDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setRoleId( (long) roleGetDto.getRoleId() );
        role.setRoleName( roleGetDto.getRoleName() );
        role.setUsers( userGetDtoSetToUserSet( roleGetDto.getUsers() ) );

        return role;
    }

    @Override
    public List<RoleGetDto> roleGetAllDto(List<Role> roles) {
        if ( roles == null ) {
            return null;
        }

        List<RoleGetDto> list = new ArrayList<RoleGetDto>( roles.size() );
        for ( Role role : roles ) {
            list.add( roleGetDto( role ) );
        }

        return list;
    }

    @Override
    public LikeDto likeDto(Like like) {
        if ( like == null ) {
            return null;
        }

        LikeDto likeDto = new LikeDto();

        likeDto.setLikeId( like.getLikeId() );

        return likeDto;
    }

    @Override
    public Like likeDtoToLike(LikeDto likeDto) {
        if ( likeDto == null ) {
            return null;
        }

        Like like = new Like();

        like.setLikeId( likeDto.getLikeId() );

        return like;
    }

    @Override
    public List<LikeDto> allLikeDtos(List<Like> likes) {
        if ( likes == null ) {
            return null;
        }

        List<LikeDto> list = new ArrayList<LikeDto>( likes.size() );
        for ( Like like : likes ) {
            list.add( likeDto( like ) );
        }

        return list;
    }

    @Override
    public LikePostDto likePostDto(Like like) {
        if ( like == null ) {
            return null;
        }

        LikePostDto likePostDto = new LikePostDto();

        return likePostDto;
    }

    @Override
    public Like likePostDtoToLike(LikePostDto likePostDto) {
        if ( likePostDto == null ) {
            return null;
        }

        Like like = new Like();

        return like;
    }

    protected Set<PostSlimDto> postSetToPostSlimDtoSet(Set<Post> set) {
        if ( set == null ) {
            return null;
        }

        Set<PostSlimDto> set1 = new LinkedHashSet<PostSlimDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Post post : set ) {
            set1.add( postSlimDto( post ) );
        }

        return set1;
    }

    protected Set<UserGetDto> userSetToUserGetDtoSet(Set<User> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserGetDto> set1 = new LinkedHashSet<UserGetDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( User user : set ) {
            set1.add( userGetDto( user ) );
        }

        return set1;
    }

    protected Set<User> userGetDtoSetToUserSet(Set<UserGetDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<User> set1 = new LinkedHashSet<User>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UserGetDto userGetDto : set ) {
            set1.add( userGetDtoToUser( userGetDto ) );
        }

        return set1;
    }
}
