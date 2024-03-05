package dev.xenitane.studentmanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.xenitane.studentmanagement.model.Subject;
import dev.xenitane.studentmanagement.repository.SubjectRepository;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public Subject addSubject(Subject subject) throws Exception {
        if (subject == null || subject.getSubjectName() == null) {
            throw new IllegalArgumentException("No data entered.");
        }
        subject.setSubjectName(subject.getSubjectName().trim());
        if (subject.getSubjectName().isEmpty()) {
            throw new IllegalArgumentException("No data entered.");
        }
        try {
            return subjectRepository.save(subject);
        } catch (Exception e) {
            throw new IllegalArgumentException("Subject already exist");
        }

    }

    public List<Subject> addSubjects(Map<String, Object> subjectListObject) throws Exception {
        Object subjectListIteratorObject = subjectListObject.get("subjects");
        if (subjectListIteratorObject instanceof Iterable) {
            Iterable<Subject> subjectListIterable = (Iterable<Subject>) subjectListIteratorObject;
            for (Subject subject : subjectListIterable) {
                this.addSubject(subject);
            }
        }
        throw new IllegalArgumentException("Invalid data sent.");
    }

    public Subject getSubject(String subjectName) throws Exception {
        if (subjectName == null) {
            throw new IllegalArgumentException("Invalid data sent.");
        }
        subjectName = subjectName.trim();
        if (subjectName.isEmpty()) {
            throw new IllegalArgumentException("Invalid data sent.");
        }
        return subjectRepository.findBySubjectName(subjectName).orElseThrow(() -> {
            return new NullPointerException("No subject found with this name");
        });
    }

    public List<Subject> getAllSubjects() throws Exception {
        return subjectRepository.findAll();
    }

}
