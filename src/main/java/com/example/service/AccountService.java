package com.example.service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.entity.*;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account addAccount(Account newAccount) {
        return accountRepository.save(newAccount);
    }

    public Account loginAccount(String username, String password){
        return accountRepository.findByUsernameAndPassword(username, password).orElse(null);
    }

    public Account accountById(Integer accountId){
        return accountRepository.findByAccountId(accountId).orElse(null);
    }

    public List<Account> getAccountList() {
        return (List<Account>) accountRepository.findAll();
    }

}