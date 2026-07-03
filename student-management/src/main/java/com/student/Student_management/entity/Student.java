package com.student.Student_management.entity;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String course;
    private String city;
    private String department;
    private String email;

    public Student() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getCity() {
    return city;
}

public void setCity(String city) {
    this.city = city;
}

public String getDepartment() {
    return department;
}

public void setDepartment(String department) {
    this.department = department;
}
}