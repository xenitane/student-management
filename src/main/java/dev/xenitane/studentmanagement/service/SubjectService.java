package dev.xenitane.studentmanagement.service;

import java.util.ArrayList;
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
        List<Subject> subjects = new ArrayList<>();
        if (subjectListIteratorObject instanceof Iterable) {
            Iterable<Map<String, Object>> subjectListIterable = (Iterable<Map<String, Object>>) subjectListIteratorObject;
            for (Map<String, Object> subjectMap : subjectListIterable) {
                Subject subject = Subject.builder().subjectName((String) subjectMap.get("name")).build();
                subjects.add(this.addSubject(subject));
            }
            return subjects;
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
        List<Subject> subjects = subjectRepository.findAll();
        subjects.sort((a, b) -> {
            return (int) (a.getSubjectId() - b.getSubjectId());
        });
        return subjects;
    }

}
