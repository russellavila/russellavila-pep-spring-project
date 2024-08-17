package com.example.service;
import com.example.entity.*;
import com.example.exception.ResourceNotFoundException;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.repository.MessageRepository;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


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

    public Message findMessage(int messageId){
        return messageRepository.findById(messageId).orElse(null);
    }

    public Message patchMessage (int messageId, String messageText){
        Message message = messageRepository.findById(messageId).orElse(null);
        message.setMessageText(messageText);
        return messageRepository.save(message);
    }

    public long deleteMessage(int messageId){
        long x = messageRepository.count();
        messageRepository.deleteById(messageId);
        long y = messageRepository.count();
        return x-y;
    }
}