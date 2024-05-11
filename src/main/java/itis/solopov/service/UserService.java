package itis.solopov.service;

import itis.solopov.config.MailConfig;
import itis.solopov.dto.LogInUserRequestDto;
import itis.solopov.dto.UpdateUserRequestDto;
import itis.solopov.model.Sport;
import itis.solopov.model.User;
import itis.solopov.repository.SportRepository;
import itis.solopov.repository.UserRepository;
import itis.solopov.service.exception.SportNotFoundException;
import itis.solopov.service.exception.UserNotFoundException;
import itis.solopov.service.exception.WrongPasswordException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SportRepository sportRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, MailConfig mailConfig, JavaMailSender javaMailSender, SportRepository sportRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.sportRepository = sportRepository;
        this.encoder = encoder;
    }

    public User logIn(LogInUserRequestDto dto) throws WrongPasswordException {
        User user = userRepository.findByEmail(dto.email).orElse(null);
        if (user != null) {
            if (encoder.matches(dto.password, user.getPassword())) {
                return user;
            } else {
                throw new WrongPasswordException("Wrong password");
            }
        } else {
            throw new UsernameNotFoundException("User with such email not found");
        }
    }

    public User getUserById(String id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public Boolean updateUser(UpdateUserRequestDto dto) {

        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Sport sport = null;
        if (dto.getSportName() != null) {
            sport = sportRepository.findByName(dto.getSportName())
                    .orElseThrow(() -> new SportNotFoundException("Kind of sport with the name " + dto.getSportName() + " was not found"));
        }



        user.setSport(sport);

        String password;
        if (encoder.matches(dto.getPassword(), user.getPassword()) || dto.getPassword().equals(user.getPassword())) {
            password = user.getPassword();
        } else {
            password = encoder.encode(dto.getPassword());

        }
        
        userRepository.updateUser(
                password,
                dto.getName(),
                dto.getAge(),
                dto.getGender(),
                dto.getPhoto(),
                dto.getExperience(),
                dto.getDescription(),
                dto.getRating(),
                dto.getNumberOfRatings(),
                dto.getHourlyRate(),
                dto.getIsInstructor(),
                dto.getSportName(),
                dto.getId()
        );

        return true;
    }


}
