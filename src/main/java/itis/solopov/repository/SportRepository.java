package itis.solopov.repository;

import itis.solopov.model.Sport;
import jdk.jfr.internal.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SportRepository extends JpaRepository<Sport, Repository> {
    Optional<Sport> findById(Integer id);

    Optional<Sport> findByName(String name);
}
