package com.example.service;
import com.example.entity.*;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message addNewMessage(Message newMessage) {
        return messageRepository.save(newMessage);
    }

    public List<Message> getMessageList() {
        return (List<Message>) messageRepository.findAll();
    }
}