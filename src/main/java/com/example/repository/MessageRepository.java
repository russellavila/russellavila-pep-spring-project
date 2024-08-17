package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.*;
import com.example.entity.*;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List <Message> findAllMessagesByPostedBy(Integer postedBy);

@Modifying
@Query("update Message u set u.messageText = :messageText where u.messageId = :id")
    void setMessageTextById(Integer id, String messageText);
}
