package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public interface MessageService {

    public ResponseEntity<?> sendMessage(Message message);

    public ResponseEntity<?> receiveMessage(Message message);

    public ResponseEntity<?> updateMessage(Message message);

    public ResponseEntity<?> deleteMessage(int id);


    public ResponseEntity<?> getAllMessages();

    public ResponseEntity<?> getMessageById(int id);

    public ResponseEntity<?> getMessageBySender(int senderId);

    public ResponseEntity<?> getMessageByReceiver(int receiverId);


}
