package com.tonde.maisonchapback.services.implementation;

import com.tonde.maisonchapback.models.workflows.Message;
import com.tonde.maisonchapback.models.workflows.user.User;
import com.tonde.maisonchapback.repositories.MessageRepository;
import com.tonde.maisonchapback.repositories.UserRepository;
import com.tonde.maisonchapback.services.interfaces.MessageService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Hidden
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<?> sendMessage(Message message) {
       try {
           messageRepository.save(message);
           return ResponseEntity.ok("Message envoyé avec succès");
       }
         catch (Exception e){
              return ResponseEntity.badRequest().body("Erreur lors de l'envoi du message");
         }
    }

    @Override
    public ResponseEntity<?> receiveMessage(Message message) {

        //update response status
        Optional<Message> messageOptional = messageRepository.findById(message.getId());
        if(messageOptional.isPresent()){
            messageOptional.get().setResponse(message.getResponse());
            messageOptional.get().setDateResponse(message.getDateResponse());
            messageRepository.save(messageOptional.get());
            return ResponseEntity.ok("Message reçu avec succès");
        }
        else{
            return ResponseEntity.badRequest().body("Message non trouvé");
        }

    }

    @Override
    public ResponseEntity<?> updateMessage(Message message) {
          Optional<Message> messageOptional = messageRepository.findById(message.getId());
        if(messageOptional.isPresent()) {
            messageOptional.get().setSender(message.getSender());
            messageOptional.get().setReceiver(message.getReceiver());
            messageOptional.get().setSentAt(message.getSentAt());
            messageOptional.get().setResponse(message.getResponse());
            messageOptional.get().setDateResponse(message.getDateResponse());
            messageOptional.get().setMessage(message.getMessage());
            messageRepository.save(messageOptional.get());
            return ResponseEntity.ok("Message modifié avec succès");
        }
        else{
            return ResponseEntity.badRequest().body("Message non trouvé");
        }
    }

    @Override
    public ResponseEntity<?> deleteMessage(int id) {
        Optional<Message> messageOptional = messageRepository.findById(id);
        if(messageOptional.isPresent()){
            messageRepository.delete(messageOptional.get());
            return ResponseEntity.ok("Message supprimé avec succès");
        }
        else{
            return ResponseEntity.badRequest().body("Message non trouvé");
        }
    }

    @Override
    public ResponseEntity<List<Message>> getAllMessages() {
        return messageRepository.findAll().isEmpty() ? null : ResponseEntity.ok(messageRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getMessageById(int id) {
        Optional<Message> messageOptional = messageRepository.findById(id);
        if(messageOptional.isPresent()){
            return ResponseEntity.ok(messageOptional.get());
        }
        else{
            return ResponseEntity.badRequest().body("Message non trouvé");
        }

    }

    @Override
    public ResponseEntity<?> getMessageBySender(int senderId) {
        Optional<User> userOptional = userRepository.findById(senderId);
        if(userOptional.isPresent()){
            return ResponseEntity.ok(messageRepository.findBySender(userOptional.get()));
        }
        else{
            return ResponseEntity.badRequest().body("Utilisateur non trouvé");
        }

    }

    @Override
    public ResponseEntity<?> getMessageByReceiver(int receiverId) {
        Optional<User> userOptional = userRepository.findById(receiverId);
        if(userOptional.isPresent()){
            return ResponseEntity.ok(messageRepository.findByReceiver(userOptional.get()));
        }
        else{
            return ResponseEntity.badRequest().body("Utilisateur non trouvé");
        }

    }
}
