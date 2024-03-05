package dev.xenitane.studentmanagement.repository;

import dev.xenitane.studentmanagement.model.Student;
import dev.xenitane.studentmanagement.model.StudentId;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, StudentId> {
    public Optional<Student> findByStudentClassAndStudentRollNumber(Integer studentClass, Integer studentRollNumber);

    public List<Student> findAllByStudentClass(Integer studentClass);
}
