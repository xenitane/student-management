package dev.xenitane.studentmanagement.service;

import dev.xenitane.studentmanagement.model.Student;
import dev.xenitane.studentmanagement.repository.StudentRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student getStudent(Integer studentClass, Integer studentRollNumber) throws Exception {
        if (studentClass <= 0 || studentRollNumber <= 0) {
            throw new IllegalArgumentException("invalid data provided");
        }
        return studentRepository.findByStudentClassAndStudentRollNumber(studentClass, studentRollNumber)
                .orElseThrow(() -> {
                    return new NullPointerException("No student with such details is found.");
                });
    }

    public List<Student> getAllStudentsByClass(Integer studentClass) throws Exception {
        if (studentClass <= 0) {
            throw new IllegalArgumentException("invalid data provided");
        }
        return studentRepository.findAllByStudentClass(studentClass);
    }

    public Student addStudent(Student student) throws Exception {
        if (student == null) {
            throw new IllegalArgumentException("No data provided.");
        }
        if (student.getStudentName() == null) {
            throw new IllegalArgumentException("There are some empty fields.");
        }
        student.setStudentName(student.getStudentName().trim());
        if (student.getStudentClass() <= 0 || student.getStudentRollNumber() <= 0
                || student.getStudentName().isEmpty()) {
            throw new IllegalArgumentException("There are some empty fields.");
        }
        Optional<Student> optionalStudent = studentRepository.findByStudentClassAndStudentRollNumber(
                student.getStudentClass(), student.getStudentRollNumber());
        if (optionalStudent.isEmpty()) {
            return studentRepository.save(student);
        } else {
            throw new IllegalArgumentException("Student already exist");
        }
    }

    public List<Student> addStudents(Map<String, Object> studentListObject) throws Exception {
        Object studentListIteratorObject = studentListObject.get("students");
        if (studentListIteratorObject instanceof Iterable) {
            Iterable<Student> studentListIterator = (Iterable<Student>) studentListObject.get("students");
            for (Student student : studentListIterator) {
                try {
                    this.addStudent(student);
                } catch (Exception e) {
                    throw e;
                }
            }
        }
        throw new IllegalArgumentException("Invalid data sent.");
    }

    public Map<String, Object> getAllStudentsOfClass(Integer studentClass) {
    }
}
