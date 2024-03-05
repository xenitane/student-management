package dev.xenitane.studentmanagement.service;

import dev.xenitane.studentmanagement.model.Student;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentService {

//    @Autowired
//    private StudentRepository studentRepository;
    public void getStudent(Integer studentId) {
        if(studentId == null){
            return throw new NullPointerException("Please enter data");
        }
//        return studentRepository.find
    }
}
