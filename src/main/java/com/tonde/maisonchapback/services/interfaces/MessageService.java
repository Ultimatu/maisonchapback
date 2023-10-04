package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.domains.Message;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Hidden
public interface MessageService {

    ResponseEntity<String> sendMessage(Message message);

    ResponseEntity<String> receiveMessage(Message message);

    ResponseEntity<String> updateMessage(Message message);

    ResponseEntity<String> deleteMessage(int id);


    List<Message> getAllMessages();

    Message getMessageById(int id);

    List<Message> getMessageBySender(int senderId);

    List<Message> getMessageByReceiver(int receiverId);


}
