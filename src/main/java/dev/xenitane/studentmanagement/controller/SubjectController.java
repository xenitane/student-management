package dev.xenitane.studentmanagement.controller;

import java.util.List;
import java.util.Map;

import dev.xenitane.studentmanagement.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.xenitane.studentmanagement.service.SubjectService;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping(path = "/get-subject")
    public ResponseEntity<Map<String, Object>> getSubject(@RequestParam ("name") String subjectName) {
        try {
            return new ResponseEntity<>(Map.of("subject", subjectService.getSubject(subjectName)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/get-subjects")
    public ResponseEntity<Map<String, Object>> getSubjects() {
        try {
            return new ResponseEntity<>(Map.of("subjects", subjectService.getAllSubjects()),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/add-subject", produces = "Application/json", consumes = "Application/json")
    public ResponseEntity<Map<String, Object>> addSubject(@RequestBody Subject newSubject) {
        try {
            return new ResponseEntity<>(Map.of("subject", subjectService.addSubject(newSubject)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/add-subjects", produces = "Application/json", consumes = "Application/json")
    public ResponseEntity<Map<String, Object>> addSubjects(@RequestBody Map<String,Object> newsubjectList) {
        try {
            List<Subject> subjects = subjectService.addSubjects(newsubjectList);
            return new ResponseEntity<>(Map.of("subjects", subjects), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
