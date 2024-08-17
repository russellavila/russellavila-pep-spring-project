package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.example.entity.*;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List <Message> findAllMessagesByPostedBy(Integer postedBy);
}
