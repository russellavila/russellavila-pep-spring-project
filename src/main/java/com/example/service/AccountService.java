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

    public Account addNewAccount(Account newAccount) {
        return accountRepository.save(newAccount);
    }

    public List<Account> getAccountList() {
        return (List<Account>) accountRepository.findAll();
    }

}