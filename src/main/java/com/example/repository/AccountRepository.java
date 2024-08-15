package com.example.repository;
import com.example.entity.*;
import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account save (Account newAccount);
}
