package com.student.Student_management.repository;

import com.student.Student_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByName(String name);

    List<Student> findByEmail(String email);

    List<Student> findByDepartment(String department);

    List<Student> findByCity(String city);

    Student findByEmailAndPassword(String email, String password);
    List<Student> findByStatus(String status);

}