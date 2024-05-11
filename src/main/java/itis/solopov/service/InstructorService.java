package itis.solopov.service;

import itis.solopov.model.User;
import itis.solopov.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    private final UserRepository userRepository;

    public InstructorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getInstructorsBySportId(Integer id) {
        return userRepository.findAllBySportId(id);
    }
}
