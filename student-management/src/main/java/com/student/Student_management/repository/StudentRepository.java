package com.student.Student_management.repository;

import com.student.Student_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByName(String name);

    List<Student> findByEmail(String email);

    List<Student> findByDepartment(String department);

    List<Student> findByCity(String city);

    Student findByEmailAndPassword(String email, String password);

    // New Method for Signup
    Student findFirstByEmail(String email);

}