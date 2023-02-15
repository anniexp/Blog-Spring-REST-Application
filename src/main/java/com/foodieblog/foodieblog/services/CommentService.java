package com.foodieblog.foodieblog.services;

import com.foodieblog.foodieblog.dtos.CommentGetAllDto;
import com.foodieblog.foodieblog.dtos.CommentPostDto;
import com.foodieblog.foodieblog.dtos.CommentSlimDto;
import com.foodieblog.foodieblog.entities.Comment;
import com.foodieblog.foodieblog.entities.Post;
import com.foodieblog.foodieblog.entities.User;
import com.foodieblog.foodieblog.exceptionHandlers.CommentNotFoundException;
import com.foodieblog.foodieblog.exceptionHandlers.NotValidJsonBodyException;
import com.foodieblog.foodieblog.mapstruct.mappers.MapStructMapper;
import com.foodieblog.foodieblog.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.foodieblog.foodieblog.validators.JsonRequestValidator.validateJsonInput;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;
    private final MapStructMapper mapStructMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService, MapStructMapper mapStructMapper) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
        this.mapStructMapper = mapStructMapper;
    }

    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    public Comment findById(long id) throws CommentNotFoundException {
        return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment not found due to invalid comment id:" + id));
    }

    public Comment setComment(CommentPostDto commentPostDto) throws NotValidJsonBodyException {
        validateJsonInput(commentPostDto);

        Comment newComment = mapStructMapper.commentPostDtoToComment(commentPostDto);
        int authorId = commentPostDto.getAuthorId();
        User author = userService.findById(authorId).orElseThrow(() -> new NotValidJsonBodyException("User not found due to invalid user id: " + authorId));
        int postId = commentPostDto.getPostId();
        Post post = postService.findById(postId).orElseThrow(() -> new NotValidJsonBodyException("Post not found due to invalid post id: " + postId));

        newComment.setAuthorUsername(author);
        newComment.setPostOfComment(post);

        return newComment;
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public CommentGetAllDto updateComment(int id, CommentSlimDto commentSlimDto) throws CommentNotFoundException {
        Comment oldComment = findById(id);
        editComment(oldComment, commentSlimDto);
        CommentGetAllDto commentGetAllDto = mapStructMapper.commentGetAllDto(oldComment);
        commentGetAllDto.setAuthorUsernamee(oldComment.getAuthorUsername().getUsername());
        commentGetAllDto.setPostId(oldComment.getPostOfComment().getPostId().intValue());

        return commentGetAllDto;
    }

    public void editComment(Comment comment, CommentSlimDto updated) {
        comment.setText(updated.getText());
        commentRepository.save(comment);
    }

    public CommentGetAllDto getCommentGetAllDtoById(int id) throws CommentNotFoundException {
        Comment comment = commentRepository.findById((long) id).orElseThrow(() -> new CommentNotFoundException("Comment not found due to invalid comment id:" + id));
        CommentGetAllDto commentGetAllDto = mapStructMapper.commentGetAllDto(comment);

        User user = comment.getAuthorUsername();
        commentGetAllDto.setAuthorUsernamee(user.getUsername());
        Post post = comment.getPostOfComment();
        commentGetAllDto.setPostId(Math.toIntExact(post.getPostId()));

        return commentGetAllDto;
    }

    public List<CommentGetAllDto> getAllComments() throws CommentNotFoundException {
        List<CommentGetAllDto> commentList = mapStructMapper.commentGetAllDto(commentRepository.findAll());

        for (int i = 0; i < commentList.size(); i++) {
            int id = Math.toIntExact(commentList.get(i).getCommentId());
            commentList.set(i, getCommentGetAllDtoById(id));
        }

        return commentList;
    }
}
