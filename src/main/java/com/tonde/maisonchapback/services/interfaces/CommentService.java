package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.domains.Comments;
import org.springframework.http.ResponseEntity;

import java.util.List;





/**
 * Service Interface for managing {@link com.tonde.maisonchapback.domains.Comments}.
 */

public interface CommentService {


    /**
     *
     * @return List
     */
    List<Comments> getAllComments();

    Comments getCommentById(int id);

    ResponseEntity<String> addComment(Comments comments);

    ResponseEntity<String> updateComment(Comments comments);

    ResponseEntity<String> deleteComment(int id);

    List<Comments> getAllCommentsByUserId(int userId);
}
