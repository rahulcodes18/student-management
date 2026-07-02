package com.student.Student_management.service;

import com.student.Student_management.entity.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.Student_management.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository repository;

    public Student addStudent(Student student) {
        return repository.save(student);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }
    public Student updateStudent(Long id, Student student) {

    Student existingStudent = repository.findById(id).orElse(null);

    if (existingStudent != null) {
        existingStudent.setName(student.getName());
        existingStudent.setCourse(student.getCourse());

        return repository.save(existingStudent);
    }

    return null;
}
public void deleteStudent(Long id) {
    repository.deleteById(id);
}
}