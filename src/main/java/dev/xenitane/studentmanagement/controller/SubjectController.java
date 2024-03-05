package dev.xenitane.studentmanagement.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.xenitane.studentmanagement.service.SubjectService;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping(path = "/get-subject")
    public ResponseEntity<Map<String, Object>> getSubject() {
        return null;
    }

    @GetMapping(path = "/get-subjects")
    public ResponseEntity<Map<String, Object>> getSubjects() {
        return null;
    }

    @PostMapping(path = "/add-subject")
    public ResponseEntity<Map<String, Object>> addSubject() {
        return null;
    }

    @PostMapping(path = "/add-subjects")
    public ResponseEntity<Map<String, Object>> addSubjects() {
        return null;
    }
}
