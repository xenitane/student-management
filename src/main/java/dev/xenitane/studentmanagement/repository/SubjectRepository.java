package dev.xenitane.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.xenitane.studentmanagement.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    
}
