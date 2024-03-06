package dev.xenitane.studentmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.xenitane.studentmanagement.model.Subject;
import dev.xenitane.studentmanagement.model.SubjectId;

public interface SubjectRepository extends JpaRepository<Subject, SubjectId> {
    public Optional<Subject> findBySubjectName(String subjectName);

    public Optional<Subject> findBySubjectId(Long subjectId);
}
