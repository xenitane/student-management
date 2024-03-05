package dev.xenitane.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.xenitane.studentmanagement.model.Subject;
import dev.xenitane.studentmanagement.repository.SubjectRepository;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public Subject addSubject(Subject subject) throws Exception {
        return null;
    }

    public List<Subject> addSubjects(Object subjectListObject) throws Exception {
        return null;
    }

    public Subject getSubject(String name) throws Exception {
        return null;
    }

    public List<Subject> getAllSubjects() throws Exception {
        return null;
    }

}
