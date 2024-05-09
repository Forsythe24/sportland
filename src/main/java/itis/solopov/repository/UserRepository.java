package itis.solopov.repository;

import itis.solopov.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    List<User>  findAllByName(String name);

    Optional<User> findByEmail(String email);

    @Query(value = "select * from users u where u.name = ?1", nativeQuery = true)
    List<User> findAllByName1(String name);

    @Query(value = "select u from User u where u.name = :name")
    List<User> findAllByName2(String name);

//    @Modifying
//    @Query("update User u set u.name = ?1, u.lastname = ?2 where u.id = ?3")
//    void setUserInfoById(String name, String lastname, Integer id);
}
