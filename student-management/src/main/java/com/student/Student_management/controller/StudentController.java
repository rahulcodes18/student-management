package com.student.Student_management.controller;

import com.student.Student_management.entity.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.student.Student_management.service.StudentService;

import java.util.List;
import org.springframework.data.domain.Page;

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

@GetMapping("/search")
public List<Student> searchByName(@RequestParam String name) {
    return service.searchByName(name);
}
@GetMapping("/search/email")
public List<Student> searchByEmail(@RequestParam String email) {
    return service.searchByEmail(email);
}
@GetMapping("/search/department")
public List<Student> searchByDepartment(@RequestParam String department) {
    return service.searchByDepartment(department);
}
@GetMapping("/search/city")
public List<Student> searchByCity(@RequestParam String city) {
    return service.searchByCity(city);
}
@GetMapping("/pagination")
public Page<Student> getStudentsWithPagination(
        @RequestParam int page,
        @RequestParam int size) {

    return service.getStudentsWithPagination(page, size);
}
@GetMapping("/sort/asc")
public List<Student> getStudentsAsc(@RequestParam String field) {
    return service.getStudentsAsc(field);
}
@GetMapping("/sort/desc")
public List<Student> getStudentsDesc(@RequestParam String field) {
    return service.getStudentsDesc(field);
}
}