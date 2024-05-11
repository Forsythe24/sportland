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
    @Transactional
    @Query("update User u set u.password = ?1, u.name = ?2, u.age = ?3, u.gender = ?4, u.photo = ?5, u.experience = ?6, u.description = ?7, u.rating = ?8, u.numberOfRatings = ?9, u.hourlyRate = ?10, u.isInstructor = ?11, u.sportName = ?12 where u.id = ?13")
    void updateUser(String password, String name, Integer age, String gender, String photo, String experience, String description, Float rating, Integer numberOfRatings, Float hourlyRate, Boolean isInstructor, String sportName, String id);
}
