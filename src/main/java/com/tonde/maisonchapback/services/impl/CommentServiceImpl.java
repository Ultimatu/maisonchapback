package com.tonde.maisonchapback.services.impl;

import com.tonde.maisonchapback.domains.Comments;
import com.tonde.maisonchapback.domains.User;
import com.tonde.maisonchapback.repositories.CommentRepository;
import com.tonde.maisonchapback.repositories.HouseRepository;
import com.tonde.maisonchapback.repositories.UserRepository;
import com.tonde.maisonchapback.services.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final HouseRepository houseRepository;


    @Override
    public List<Comments> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comments getCommentById(int id) {
        Optional<Comments> comments = commentRepository.findById(id);
        return comments.orElse(null);
    }

    @Override
    public ResponseEntity<String> addComment(Comments comments) {


        commentRepository.save(comments);
        return ResponseEntity.ok("Commentaire ajouté");
    }

    @Override
    public ResponseEntity<String> updateComment(Comments comments) {
        Optional<Comments> comments1 = commentRepository.findById(comments.getId());
        if (comments1.isPresent()) {

            comments1.get().setDateModification(comments.getDateModification());
            comments1.get().setUser(comments.getUser());
            comments1.get().setHouse(comments.getHouse());
            comments1.get().setComment(comments.getComment());
            commentRepository.save(comments1.get());
            return ResponseEntity.ok("Commentaire modifié avec succès");
        } else {
            return ResponseEntity.badRequest().body("Commentaire non trouvé");
        }
    }

    @Override
    public ResponseEntity<String> deleteComment(int id) {

        Optional<Comments> comments1 = commentRepository.findById(id);
        if (comments1.isPresent()) {
            commentRepository.delete(comments1.get());
            return ResponseEntity.ok("Commentaire supprimé avec succès");
        } else {
            return ResponseEntity.badRequest().body("Commentaire non trouvé");
        }
    }

    @Override
    public List<Comments> getAllCommentsByUserId(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return commentRepository.findAllByUser(user);
        } else {
            return new ArrayList<>();
        }
    }

}
