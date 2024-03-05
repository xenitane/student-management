package dev.xenitane.studentmanagement.controller;

import java.util.List;

import dev.xenitane.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.xenitane.studentmanagement.model.Student;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping(path = "/add-student",consumes = "Application/json", produces = "Application/json")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        System.out.println(student);
        return new ResponseEntity<Student>(student, HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/add-students",produces = "Application/json", consumes = "Application/json")
    public ResponseEntity<List<Student>> addStudents(@RequestBody Object studentList){
        
        return null;
    }

    @GetMapping(path = "/get-student")
    public ResponseEntity<Student> getStudent(@RequestParam("studentId") Long studentId){
        try{
            return ResponseEntity.ok(studentService.getStudent(studentId));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
}
