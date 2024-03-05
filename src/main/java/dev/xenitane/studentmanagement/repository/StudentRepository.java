package dev.xenitane.studentmanagement.repository;

import dev.xenitane.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
