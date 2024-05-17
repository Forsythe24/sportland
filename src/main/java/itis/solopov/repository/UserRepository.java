package itis.solopov.repository;

import itis.solopov.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User>  findAllByName(String name);

    Optional<User> findByEmail(String email);

    Optional<User> findById(String id);

    @Query(value = "select * from users u where u.name = ?1", nativeQuery = true)
    List<User> findAllByName1(String name);

    @Query(value = "SELECT * FROM user_sport JOIN users ON users.id = user_sport.user_id WHERE user_sport.sport_id = ?1", nativeQuery = true)
    List<User> findAllBySportId(Integer sportId);

    @Modifying
    @Query("update User u set u.name = ?1, u.age = ?2, u.gender = ?3, u.photo = ?4, u.experience = ?5, u.description = ?6, u.rating = ?7, u.numberOfRatings = ?8, u.hourlyRate = ?9, u.isInstructor = ?10, u.sportName = ?11 where u.id = ?12")
    void updateUser(String name, Integer age, String gender, String photo, String experience, String description, Float rating, Integer numberOfRatings, Float hourlyRate, Boolean isInstructor, String sportName, String id);

    @Modifying
    @Query("update User u set u.password = ?1 where u.email = ?2")
    void updatePassword(String password, String email);
}
