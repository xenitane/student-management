package dev.xenitane.studentmanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.xenitane.studentmanagement.model.Student;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @PostMapping(path = "/add-student",consumes = "Application/json", produces = "Application/json")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        System.out.println(student);
        return new ResponseEntity<Student>(student, HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/add-students",produces = "Application/json", consumes = "Application/json")
    public ResponseEntity<List<Student>> addStudents(@RequestBody Object studentList){
        
        return null;
    }
}
