package dev.xenitane.studentmanagement.repository;

import dev.xenitane.studentmanagement.model.Student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    public Optional<Student> findByStudentClassAndStudentRollNumber(Integer studentClass, Integer studentRollNumber);
}
