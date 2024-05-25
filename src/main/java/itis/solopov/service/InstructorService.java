package itis.solopov.service;

import itis.solopov.model.User;
import itis.solopov.repository.UserRepository;
import itis.solopov.service.exception.ServiceUnavailableException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    private final UserRepository userRepository;

    public InstructorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getInstructorsBySportId(Integer id) {
        try {
            return userRepository.findAllBySportId(id);
        } catch (Exception ex) {
            throw new ServiceUnavailableException("The service is not available at the moment. Please, consider trying again later");
        }
    }
}
