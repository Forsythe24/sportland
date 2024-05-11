package itis.solopov.controller;

import itis.solopov.model.User;
import itis.solopov.service.InstructorService;
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

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("{id}")
    public ResponseEntity<List<User>> getInstructorsBySportId(@PathVariable int id) {
        System.out.println(id);
        return ResponseEntity.ok(instructorService.getInstructorsBySportId(id));
    }
}