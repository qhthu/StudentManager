package com.example.apidemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sun.font.Script;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("StudentServices")
@Profile("prod")
public class StudentServices {


    @Autowired
    private StudentRepositories studentRepositories;


    public Student saveStudent(Student student){
        return (Student)studentRepositories.save(student);
    }

//    Page<Student> allStudentSortedByName = (Page<Student>) studentRepositories.findAll(Sort.by("name"));
    public Student updateStudent(Student studentUpdate){
        Student student = studentRepositories.getOne(studentUpdate.getId());

        if(student != null){
            student.setAddress(studentUpdate.getAddress());
            student.setDayofbirth(studentUpdate.getDayofbirth());
            student.setEmail(studentUpdate.getEmail());
            student.setName(studentUpdate.getName());
            return (Student)studentRepositories.save(student);
        }
        return (Student)studentRepositories.save(student);
    }

    public Boolean deleteStudent(String id){
        Student student = studentRepositories.getOne(id);
        if(student != null){
            studentRepositories.delete(student);
            return true;
        }
        return false;
    }

    public List<Student> getAllStudent(){
        return studentRepositories.findAll();
    }

    public Optional<Student> getStudent(String id){
        return studentRepositories.findById(id);
    }
}
