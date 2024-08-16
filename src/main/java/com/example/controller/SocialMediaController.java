package com.example.controller;
import com.example.repository.*;
import com.example.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.service.MessageService;
import com.example.service.AccountService;
import com.example.repository.AccountRepository;
import java.util.List;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    private final AccountService accountService;
    private final MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    Account newAccount(@RequestBody Account newAccount) {
        return accountService.addAccount(newAccount);
    }    

    @GetMapping("/messages")
    List<Message> allMessages() {
        return messageService.getMessageList();
    }
    
    @GetMapping("/messages/{messageId}")
    Message getMessageById(@PathVariable Integer messageId) {
      return messageService.findMessage(messageId);
    }
}