package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.Comments;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Hidden
public interface CommentService {

    List<Comments> getAllComments();

    Comments getCommentById(int id);

    ResponseEntity<String> addComment(Comments comments);

    ResponseEntity<String> updateComment(Comments comments);

    ResponseEntity<String> deleteComment(int id);

    List<Comments> getAllCommentsByUserId(int userId);
}
