package com.student.Student_management.controller;

import com.student.Student_management.entity.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.student.Student_management.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService service;

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return service.addStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }
    @PutMapping("/{id}")
public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
    return service.updateStudent(id, student);
}
@DeleteMapping("/{id}")
public String deleteStudent(@PathVariable Long id) {
    service.deleteStudent(id);
    return "Student Deleted Successfully";
}
}