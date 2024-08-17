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
import java.util.Collection;
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
    ResponseEntity <Account> newAccount(@RequestBody Account newAccount) {
        if ((newAccount.getUsername() == "")||(newAccount.getPassword().length()<4)){
            return ResponseEntity.status(400).body(null);
        }
        if (accountService.loginAccount(newAccount.getUsername(), newAccount.getPassword()) != null){
            return ResponseEntity.status(409).body(null);
        }
        return ResponseEntity.ok(accountService.addAccount(newAccount));
    }
    
    @PostMapping("/messages")
    ResponseEntity <Message> newMessage(@RequestBody Message newMessage) {
        if ((newMessage.getMessageText() == "")||(newMessage.getMessageText().length()>255)){
            return ResponseEntity.status(400).body(null);
        }
        if (getAccount(newMessage.getPostedBy()).hasBody() == false){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.ok(messageService.addNewMessage(newMessage));
    }
    
    @GetMapping("/accounts/{accountId}")
    ResponseEntity<Account> getAccount(@PathVariable Integer postedBy){
       ResponseEntity<Account> check = ResponseEntity.ok(accountService.accountById(postedBy));
        if (check.hasBody() == false){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.ok(accountService.accountById(postedBy));
    }

    @GetMapping("/messages")
    List<Message> allMessages() {
        return messageService.getMessageList();
    }
    
    @GetMapping("/messages/{messageId}")
    Message getMessageById(@PathVariable Integer messageId) {
      return messageService.findMessage(messageId);
    }

    @PostMapping("/login")
    ResponseEntity<Account> getLogin(@RequestBody Account login) {
        ResponseEntity<Account> nj = ResponseEntity.ok(accountService.loginAccount(login.getUsername(), login.getPassword()));

        if (nj.hasBody() == false){
            return ResponseEntity.status(401).body(null);
        }

        return ResponseEntity.ok(accountService.loginAccount(login.getUsername(), login.getPassword()));
    }

    @PatchMapping("/messages/{messageId}")
    ResponseEntity<Message> patchMessage(@RequestParam() Integer messageId, @RequestHeader String messageText){
        return ResponseEntity.ok(messageService.patchMessage(messageId, messageText));
    }

    @DeleteMapping("/messages/{messageId}")
    ResponseEntity<Long> deleteMessage(@PathVariable Integer messageId){
        ResponseEntity<Message> checkMessage = ResponseEntity.ok(messageService.findMessage(messageId));
        if(checkMessage.hasBody() == false){
            return null;
        }

        return ResponseEntity.ok(messageService.deleteMessage(messageId));
    }

    @GetMapping("/accounts/{accountId}/messages")
    List <Message> allMessagesByAccountId(@PathVariable Integer accountId){
        return messageService.getMessagesByAccountId(accountId);
    }
    
}