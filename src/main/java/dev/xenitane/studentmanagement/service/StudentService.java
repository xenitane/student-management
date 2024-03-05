package dev.xenitane.studentmanagement.service;

import dev.xenitane.studentmanagement.model.Student;
import dev.xenitane.studentmanagement.repository.StudentRepository;

import java.util.List;
import java.util.Map;

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
        try {
            return studentRepository.save(student);
        } catch (Exception e) {
            throw new IllegalArgumentException("Student already exist");
        }
    }

    public List<Student> addStudents(Map<String, Object> studentListObject) throws Exception {
        Object studentListIteratorObject = studentListObject.get("students");
        if (studentListIteratorObject instanceof Iterable) {
            Iterable<Student> studentListIterator = (Iterable<Student>) studentListObject.get("students");
            for (Student student : studentListIterator) {
                this.addStudent(student);
            }
        }
        throw new IllegalArgumentException("Invalid data sent.");
    }

    public Object updateMarks(Map<String, Object> studentMarks) {
        return null;
    }
}
