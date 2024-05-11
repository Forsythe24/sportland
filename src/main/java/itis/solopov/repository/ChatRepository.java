package itis.solopov.repository;

import itis.solopov.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, String> {
    Optional<Chat> findById(String id);

    @Query(value = "select * from chat c where c.user_id = ?1", nativeQuery = true)
    List<Chat> findAllByUserId(String userId);
}
