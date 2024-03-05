package dev.xenitane.studentmanagement.controller;

import java.util.List;
import java.util.Map;

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

    @PostMapping(path = "/add-student", consumes = "Application/json", produces = "Application/json")
    public ResponseEntity<Map<String, Object>> addStudent(@RequestBody Student student) {
        try {
            student = studentService.addStudent(student);
            return new ResponseEntity<>(Map.of("student", student), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(path = "/add-students", produces = "Application/json", consumes = "Application/json")
    public ResponseEntity<Map<String, Object>> addStudents(@RequestBody Map<String, Object> studentList) {
        try {
            List<Student> students = studentService.addStudents(studentList);
            return new ResponseEntity<>(Map.of("students", students), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/get-student")
    public ResponseEntity<Student> getStudent(@RequestParam("roll-num") Long rollNumber, @RequestParam("class") Integer studentClass) {
        try {
            return ResponseEntity.ok(studentService.getStudent(studentClass,rollNumber));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(path = "/get-students")
    public ResponseEntity<Map<String,Object>> getStudentsOfClass(@RequestParam("class") Integer student_class) {
        try{
            return ResponseEntity.ok(studentService.getAllStudentsOfClass(student_class));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
        }
    }

}
