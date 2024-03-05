package dev.xenitane.studentmanagement.service;

import dev.xenitane.studentmanagement.model.Student;
import dev.xenitane.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    public Student getStudent(Long studentId) {
        if(studentId == null){
            throw new NullPointerException("Please enter data");
        }
        return studentRepository.findById(studentId).orElseThrow(()->{
            return new NullPointerException("not found");
        });
    }
}
