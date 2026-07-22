package com.student.Student_management.service;

import com.student.Student_management.entity.Student;
import com.student.Student_management.exception.StudentNotFoundException;
import com.student.Student_management.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository repository;

    // Add Student
    public Student addStudent(Student student) {
        return repository.save(student);
    }

    // Get All Students
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // Search By Name
    public List<Student> searchByName(String name) {
        return repository.findByName(name);
    }

    // Search By Email
    public List<Student> searchByEmail(String email) {
        return repository.findByEmail(email);
    }

    // Search By Department
    public List<Student> searchByDepartment(String department) {
        return repository.findByDepartment(department);
    }

    // Search By City
    public List<Student> searchByCity(String city) {
        return repository.findByCity(city);
    }

    // Pagination
    public Page<Student> getStudentsWithPagination(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return repository.findAll(pageable);
    }

    // Sorting Asc
    public List<Student> getStudentsAsc(String field) {
        return repository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    // Sorting Desc
    public List<Student> getStudentsDesc(String field) {
        return repository.findAll(Sort.by(Sort.Direction.DESC, field));
    }

    // Update Student
  public Student updateStudent(Long id, Student student) {

    Student existingStudent = repository.findById(id).orElse(null);

    if (existingStudent != null) {

        existingStudent.setName(student.getName());
        existingStudent.setCourse(student.getCourse());
        existingStudent.setCity(student.getCity());
        existingStudent.setDepartment(student.getDepartment());
        existingStudent.setEmail(student.getEmail());

        
        if(student.getPassword() != null){
            existingStudent.setPassword(student.getPassword());
        }

        // Status update
        existingStudent.setStatus(student.getStatus());

        return repository.save(existingStudent);
    }

    return null;
}
    // Delete Student
    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }

    // Login
    public Student login(String email, String password) {

        return repository.findByEmailAndPassword(email, password);

    }

    // Signup
    public Student signup(Student student) {

        // Check email already exists
        List<Student> existingStudents = repository.findByEmail(student.getEmail());

        if (!existingStudents.isEmpty()) {
            return null;
        }
        student.setStatus("ACTIVE");

        return repository.save(student);
    }

    // Get Student By Id
    public Student getStudentById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
            new StudentNotFoundException("Student Not Found"));
    }
    public List<Student> filterByStatus(String status) {
    return repository.findByStatus(status);
}

}