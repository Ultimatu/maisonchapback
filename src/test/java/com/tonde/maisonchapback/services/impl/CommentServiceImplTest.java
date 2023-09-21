package com.tonde.maisonchapback.services.impl;

import com.tonde.maisonchapback.domains.Comments;
import com.tonde.maisonchapback.domains.House;
import com.tonde.maisonchapback.domains.User;
import com.tonde.maisonchapback.repositories.CommentRepository;
import com.tonde.maisonchapback.repositories.HouseRepository;
import com.tonde.maisonchapback.repositories.UserRepository;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class contains unit tests for the CommentServiceImpl class.
 * It uses Mockito and JUnit to test the methods of the CommentServiceImpl class.
 * The tests include getAllComments, getCommentById, addComment, updateComment and deleteComment.
 * The commented out test method, getAllCommentsByUserId, is not currently being used.
 * The tests ensure that the methods of the CommentServiceImpl class are working as expected.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private HouseRepository houseRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void testGetAllComments() {
        List<Comments> commentsList = new ArrayList<>();
        commentsList.add(new Comments(1, new House(), new User(), "Test comment 1", Instant.now(), Instant.now()));
        commentsList.add(new Comments(2, new House(),  new User(), "Test comment 2", Instant.now(), Instant.now()));
        Mockito.when(commentRepository.findAll()).thenReturn(commentsList);
        List<Comments> result = commentService.getAllComments();
        Assert.assertEquals(2, result.size());
    }

    @Test
    void testGetCommentById() {
        Comments comment = new Comments(1, new House(), new User(), "Test comment 1", Instant.now(), Instant.now());
        Mockito.when(commentRepository.findById(1)).thenReturn(Optional.of(comment));
        Comments result = commentService.getCommentById(1);
        Assert.assertEquals(comment, result);
    }

    @Test
    void testAddComment() {
        Comments comment = new Comments(1, new House(), new User(), "Test comment 1", Instant.now(), Instant.now());
        Mockito.when(commentRepository.save(comment)).thenReturn(comment);
        ResponseEntity<String> result = commentService.addComment(comment);
        Assert.assertEquals(ResponseEntity.ok("Commentaire ajouté"), result);
    }

    @Test
    void testUpdateComment() {
        Comments comment = new Comments(1, new House(), new User(), "Test comment 1", Instant.now(), Instant.now());
        Mockito.when(commentRepository.findById(1)).thenReturn(Optional.of(comment));
        Mockito.when(commentRepository.save(comment)).thenReturn(comment);
        ResponseEntity<String> result = commentService.updateComment(comment);
        Assert.assertEquals(ResponseEntity.ok("Commentaire modifié avec succès"), result);
    }

    @Test
    void testDeleteComment() {
        Comments comment = new Comments(1, new House(), new User(), "Test comment 1", Instant.now(), Instant.now());
        Mockito.when(commentRepository.findById(1)).thenReturn(Optional.of(comment));
        ResponseEntity<String> result = commentService.deleteComment(1);
        Assert.assertEquals(ResponseEntity.ok("Commentaire supprimé avec succès"), result);
    }
/* 
    @Test
    void testGetAllCommentsByUserId() {
        List<User> users = userRepository.findAll();
        CustomLogger.log("INFO", users.toString());
        User user = users.get(0);
        List<Comments> commentsList = new ArrayList<>();
        commentsList.add(new Comments(1, new House(), new User(), "Test comment 1", Instant.now(), Instant.now()));
        commentsList.add(new Comments(1, new House(), new User(), "Test comment 1", Instant.now(), Instant.now()));
        Mockito.when(userRepository.findById(2)).thenReturn(Optional.of(user));
        Mockito.when(commentRepository.findAllByUser(user)).thenReturn(commentsList);
        List<Comments> result = commentService.getAllCommentsByUserId(2);
        Assert.assertEquals(2, result.size());
    } */
}