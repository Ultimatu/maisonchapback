package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.Message;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@Hidden
public interface MessageService {

    ResponseEntity<?> sendMessage(Message message);

    ResponseEntity<?> receiveMessage(Message message);

    ResponseEntity<?> updateMessage(Message message);

    ResponseEntity<?> deleteMessage(int id);


    ResponseEntity<?> getAllMessages();

    ResponseEntity<?> getMessageById(int id);

    ResponseEntity<?> getMessageBySender(int senderId);

    ResponseEntity<?> getMessageByReceiver(int receiverId);


}
