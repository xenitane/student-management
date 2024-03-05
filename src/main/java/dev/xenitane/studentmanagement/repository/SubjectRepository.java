package dev.xenitane.studentmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.xenitane.studentmanagement.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    public Optional<Subject> findBySubjectName(String subjectName);
}
