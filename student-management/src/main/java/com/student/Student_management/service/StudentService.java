package com.student.Student_management.service;

import com.student.Student_management.entity.Student;
import com.student.Student_management.exception.StudentNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.Student_management.repository.StudentRepository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
   public List<Student> searchByName(String name) {
    return repository.findByName(name);
    

}
public List<Student> searchByEmail(String email) {
    return repository.findByEmail(email);
}
public List<Student> searchByDepartment(String department) {
    return repository.findByDepartment(department);
}
public List<Student> searchByCity(String city) {
    return repository.findByCity(city);
}

public Page<Student> getStudentsWithPagination(int page, int size) {

    Pageable pageable = PageRequest.of(page, size);

    return repository.findAll(pageable);
}
public List<Student> getStudentsAsc(String field) {
    return repository.findAll(Sort.by(Sort.Direction.ASC, field));
}
public List<Student> getStudentsDesc(String field) {
    return repository.findAll(Sort.by(Sort.Direction.DESC, field));
}
public Student updateStudent(Long id, Student student) {

    Student existingStudent = repository.findById(id).orElse(null);

    if (existingStudent != null) {

        existingStudent.setName(student.getName());
        existingStudent.setCourse(student.getCourse());
        existingStudent.setCity(student.getCity());
        existingStudent.setDepartment(student.getDepartment());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPassword(student.getPassword());

        return repository.save(existingStudent);
    }

    return null;
}
public void deleteStudent(Long id) {
    repository.deleteById(id);
}
public Student login(String email, String password) {
    return repository.findByEmailAndPassword(email, password);
}
public Student signup(Student student) {

    Student existingStudent = repository.findByEmailAndPassword(student.getEmail(), student.getPassword());

    if (existingStudent != null) {
        return null;
    }

    return repository.save(student);
}
public Student getStudentById(Long id) {

    return repository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException("Student Not Found"));
}



}