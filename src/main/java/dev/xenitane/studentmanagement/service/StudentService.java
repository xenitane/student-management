package dev.xenitane.studentmanagement.service;

import dev.xenitane.studentmanagement.model.Student;
import dev.xenitane.studentmanagement.model.Subject;
import dev.xenitane.studentmanagement.repository.StudentRepository;
import dev.xenitane.studentmanagement.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

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
        student.setStudentName(student.getStudentName().toLowerCase().trim());
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
        List<Student> students = new ArrayList<>();
        if (studentListIteratorObject instanceof Iterable) {
            Iterable<Map<String, Object>> studentListIterator = (Iterable<Map<String, Object>>) studentListIteratorObject;
            for (Map<String, Object> studentMap : studentListIterator) {
                Student student = Student.builder()
                        .studentName((String) studentMap.get("name"))
                        .studentClass((Integer) studentMap.get("class"))
                        .studentRollNumber((Integer) studentMap.get("roll-number"))
                        .build();
                students.add(this.addStudent(student));
            }
            return students;
        }
        throw new IllegalArgumentException("Invalid data sent.");
    }

    public Student updateMarks(Map<String, Object> studentMarks) {
        Long studentId = (Long) studentMarks.get("student-id");
        if (studentId <= 0) {
            throw new IllegalArgumentException("invalid data provided");
        }
        Object studentMarksListIteratorObject = studentMarks.get("marks");
        Student student = studentRepository.findByStudentId(studentId).orElseThrow(() -> {
            return new IllegalArgumentException("invalid data provided");
        });
        if (studentMarksListIteratorObject instanceof Iterable) {
            Iterable<Map<String, Object>> studentMarksListIterator = (Iterable<Map<String, Object>>) studentMarksListIteratorObject;
            for (Map<String, Object> marksData : studentMarksListIterator) {
                Long subjectId = (Long) marksData.get("subject-id");
                if (subjectId == null || subjectId <= 0) {
                    throw new IllegalArgumentException("invalid data provided");
                }
                Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> {
                    return new IllegalArgumentException("invalid data provided");
                });
                Integer marks = (Integer) marksData.get("marks");
                if (marks < 0 || marks > 100) {
                    throw new IllegalArgumentException("invalid data provided");
                }
                student.addOrUpdateMarks(subject, marks);
                studentRepository.save(student);
                return student;
            }

        }
        throw new IllegalArgumentException("Invalid data sent.");
    }

    public List<Student> updateMarksForAll(Map<String, Object> studentsMarks) {
        Object studentsMarksIteratorObject = studentsMarks.get("students-marks");
        List<Student> students = new ArrayList<>();
        if (studentsMarksIteratorObject instanceof Iterable<?>) {
            Iterable<Map<String, Object>> studentsMarksIterator = (Iterable<Map<String, Object>>) studentsMarksIteratorObject;
            for (Map<String, Object> studentMarks : studentsMarksIterator) {
                students.add(this.updateMarks(studentMarks));
            }
            return students;
        }
        throw new IllegalArgumentException("Invalid data sent.");
    }
}
