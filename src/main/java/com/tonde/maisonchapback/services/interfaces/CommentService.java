package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.Comments;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CommentService {

    public List<Comments> getAllComments();

    public Comments getCommentById(int id);

    public ResponseEntity<?> addComment(Comments comments);

    public ResponseEntity<?> updateComment(Comments comments);

    public ResponseEntity<?> deleteComment(int id);

    public List<Comments> getAllCommentsByUserId(int userId);
}
