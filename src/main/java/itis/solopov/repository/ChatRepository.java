package itis.solopov.repository;

import itis.solopov.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, String> {
    Optional<Chat> findById(String id);

    @Query(value = "select c from Chat c where c.user.id = :userId")
    List<Chat> findAllByUserId(String userId);

    @Modifying
    @Query(value = "DELETE FROM chat WHERE id LIKE '%' || ?1 || '%'", nativeQuery = true)
    void deleteAllChatsByUserId(String userId);


    @Modifying
    @Query(value = "DELETE FROM chat_user WHERE chat_id LIKE '%' || ?1 || '%'", nativeQuery = true)
    void deleteAllChatUsers(String userId);
}
