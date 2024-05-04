package itis.solopov.controller;

import itis.solopov.dto.CreateUserRequestDto;
import itis.solopov.dto.UserDto;
import itis.solopov.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @GetMapping("/users")
    public List<UserDto> findByName(@RequestParam String name) {
        return userService.findAllByName(name);
    }

    @PostMapping("/user")
    public String create(@ModelAttribute CreateUserRequestDto user, HttpServletRequest req) {
        String url = req.getRequestURL().toString().replace(req.getServletPath(), "");
        userService.create(user, url);
        return "sign_up_success";
    }

    @GetMapping("/signup")
    public String signUp() {
        return "sign_up";
    }

    @GetMapping("/verification")
    public String verify(@Param("code") String code) {
        if (userService.verify(code)) {
            return "verification_success";
        }
        return "verification_failed";
    }
}
