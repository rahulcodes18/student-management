package com.student.Student_management.controller;

import com.student.Student_management.entity.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.student.Student_management.service.StudentService;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService service;

    @PostMapping
public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {

    Student savedStudent = service.addStudent(student);

    return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
}

    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }
   
@PutMapping("/{id}")
public ResponseEntity<Student> updateStudent(@PathVariable Long id,
                                             @RequestBody Student student) {

    Student updatedStudent = service.updateStudent(id, student);

    return ResponseEntity.ok(updatedStudent);
}
@DeleteMapping("/{id}")
public ResponseEntity<String> deleteStudent(@PathVariable Long id) {

    service.deleteStudent(id);

    return ResponseEntity.ok("Student Deleted Successfully");
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
@PostMapping("/login")
public ResponseEntity<?> login(@RequestParam String email,
                               @RequestParam String password) {

    Student student = service.login(email, password);

    if (student != null) {
        return ResponseEntity.ok(student);
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid Email or Password");
    }
}

@PostMapping("/signup")
public ResponseEntity<?> signup(@Valid @RequestBody Student student) {

    Student savedStudent = service.signup(student);

    if (savedStudent == null) {
        return ResponseEntity.badRequest().body("Email Already Exists");
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
}
@PostMapping("/logout")
public ResponseEntity<String> logout() {

    return ResponseEntity.ok("Logout Successful");

}
@GetMapping("/{id}")
public ResponseEntity<?> getStudentById(@PathVariable Long id) {

    Student student = service.getStudentById(id);

    if (student != null) {
        return ResponseEntity.ok(student);
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Student Not Found");
}
@GetMapping("/filter/status")
public List<Student> filterByStatus(@RequestParam String status) {
    return service.filterByStatus(status);
}
}