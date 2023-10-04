package com.tonde.maisonchapback.services.impl;

import com.tonde.maisonchapback.domains.Message;
import com.tonde.maisonchapback.domains.User;
import com.tonde.maisonchapback.repositories.MessageRepository;
import com.tonde.maisonchapback.repositories.UserRepository;
import com.tonde.maisonchapback.services.interfaces.MessageService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Hidden
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<String> sendMessage(Message message) {
        try {
            messageRepository.save(message);
            return ResponseEntity.ok("Message envoyé avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de l'envoi du message");
        }
    }

    @Override
    public ResponseEntity<String> receiveMessage(Message message) {

        //update response status
        Optional<Message> messageOptional = messageRepository.findById(message.getId());
        if (messageOptional.isPresent()) {
            messageOptional.get().setResponse(message.getResponse());
            messageOptional.get().setDateResponse(message.getDateResponse());
            messageRepository.save(messageOptional.get());
            return ResponseEntity.ok("Message reçu avec succès");
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @Override
    public ResponseEntity<String> updateMessage(Message message) {
        Optional<Message> messageOptional = messageRepository.findById(message.getId());
        if (messageOptional.isPresent()) {
            messageOptional.get().setSender(message.getSender());
            messageOptional.get().setReceiver(message.getReceiver());
            messageOptional.get().setSentAt(message.getSentAt());
            messageOptional.get().setResponse(message.getResponse());
            messageOptional.get().setDateResponse(message.getDateResponse());
            messageOptional.get().setContent(message.getContent());
            messageRepository.save(messageOptional.get());
            return ResponseEntity.ok("Message modifié avec succès");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<String> deleteMessage(int id) {
        Optional<Message> messageOptional = messageRepository.findById(id);
        if (messageOptional.isPresent()) {
            messageRepository.delete(messageOptional.get());
            return ResponseEntity.ok("Message supprimé avec succès");
        } else {
            return ResponseEntity.badRequest().body("Message non trouvé");
        }
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message getMessageById(int id) {
        Optional<Message> messageOptional = messageRepository.findById(id);
        return messageOptional.orElse(null);

    }

    @Override
    public List<Message> getMessageBySender(int senderId) {
        Optional<User> userOptional = userRepository.findById(senderId);
        if (!userOptional.isEmpty()) {
            return messageRepository.findBySender(userOptional.get());
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public List<Message> getMessageByReceiver(int receiverId) {
        Optional<User> userOptional = userRepository.findById(receiverId);
        return userOptional.map(messageRepository::findByReceiver).orElse(null);

    }
}
