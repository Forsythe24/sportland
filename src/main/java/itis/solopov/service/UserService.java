package itis.solopov.service;

import itis.solopov.config.MailConfig;
import itis.solopov.dto.UpdatePasswordRequestDto;
import itis.solopov.dto.UpdateUserRequestDto;
import itis.solopov.dto.UserDto;
import itis.solopov.dto.VerifyCredentialsRequestDto;
import itis.solopov.model.Sport;
import itis.solopov.model.User;
import itis.solopov.repository.ChatRepository;
import itis.solopov.repository.SportRepository;
import itis.solopov.repository.UserRepository;
import itis.solopov.service.exception.SportNotFoundException;
import itis.solopov.service.exception.UserNotFoundException;
import itis.solopov.util.Constants;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SportRepository sportRepository;
    private final ChatRepository chatRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, MailConfig mailConfig, JavaMailSender javaMailSender, SportRepository sportRepository, ChatRepository chatRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.sportRepository = sportRepository;
        this.chatRepository = chatRepository;
        this.encoder = encoder;
    }

    public UserDto getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format(Constants.USER_NOT_FOUND_EXCEPTION_ID_TEMPLATE, id)));

        UserDto userDto = new UserDto();
        userDto.setAge(user.getAge());
        userDto.setName(user.getName());
        userDto.setId(user.getId());
        userDto.setDescription(user.getDescription());
        userDto.setGender(user.getGender());
        userDto.setExperience(user.getExperience());
        userDto.setPhoto(user.getPhoto());
        userDto.setIsInstructor(user.getIsInstructor());
        userDto.setRating(user.getRating());
        userDto.setNumberOfRatings(user.getNumberOfRatings());
        userDto.setHourlyRate(user.getHourlyRate());
        userDto.setSportName(user.getSportName());


        return userDto;
    }

    public Boolean deleteUserById(String id) {
        userRepository.deleteById(id);
        chatRepository.deleteAllChatUsers(id);
        chatRepository.deleteAllChatsByUserId(id);
        return true;
    }

    @Transactional
    public Boolean updateUser(UpdateUserRequestDto dto) {

        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new UserNotFoundException(String.format(Constants.USER_NOT_FOUND_EXCEPTION_ID_TEMPLATE, dto.getId())));

        Sport sport = null;
        if (dto.getSportName() != null) {
            sport = sportRepository.findByName(dto.getSportName())
                    .orElseThrow(() -> new SportNotFoundException("Kind of sport with the name " + dto.getSportName() + " was not found"));
        }

        user.setSport(sport);
        userRepository.updateUser(
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

    public Boolean verifyCredentials(VerifyCredentialsRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(String.format(Constants.USER_NOT_FOUND_EXCEPTION_EMAIL_TEMPLATE, dto.getEmail())));

        return encoder.matches(dto.getPassword(), user.getPassword());
    }

    @Transactional
    public Boolean updatePassword(UpdatePasswordRequestDto dto) {
        userRepository.updatePassword(encoder.encode(dto.getPassword()), dto.getEmail());
        return true;
    }


}
