package itis.solopov.repository;

import itis.solopov.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE m.chatId = ?1")
    public List<Message> getAllByChatId(String chatId);

    @Query("SELECT m FROM Message m WHERE m.chatId = ?1 AND m.id = (SELECT MAX(m2.id) FROM Message m2 WHERE m2.chatId = ?1)")
    public Message findLastMessageByChatId(String chatId);
}