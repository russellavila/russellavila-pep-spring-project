package com.example.repository;
import com.example.entity.*;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.repository.CrudRepository;
import java.io.Serializable;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsernameAndPassword(String username, String password);
}

/*
 * 
 * @Repository
public interface AssociateRepository extends CrudRepository<Student, Serializable> {

    @Query("select a from Associate a where a.name = :name")
    List<Associate> findByName(@Param("name") String name);
 */