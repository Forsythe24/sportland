package itis.solopov.controller;

import itis.solopov.AuthManager;
import itis.solopov.model.User;
import itis.solopov.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/instructors", produces = "application/json")
public class InstructorController {

    private final InstructorService instructorService;
    private final AuthManager authManager;


    public InstructorController(InstructorService instructorService, AuthManager authManager) {
        this.instructorService = instructorService;
        this.authManager = authManager;
    }

    @GetMapping("{sportId}")
    public ResponseEntity<List<User>> getInstructorsBySportId(@PathVariable int sportId) {
        if (authManager.isAuthorized()) {
            return ResponseEntity.ok(instructorService.getInstructorsBySportId(sportId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}