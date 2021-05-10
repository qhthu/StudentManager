package com.example.apidemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.*;


@RestController
@RequestMapping("/api")
@Profile("prod")
public class StudentController {

    public StudentServices getData(){
        String sql = "select * from STUDENT";
        try{
            Class.forName ("org.h2.Driver");
            Connection connection = DriverManager.getConnection ("jdbc:h2:tcp://localhost/~/test", "sa","123");
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Student st = new Student();
                st.setId(rs.getString("ID"));
                st.setName(rs.getString("NAME"));
                st.setAddress(rs.getString("ADDRESS"));
                st.setDayofbirth(rs.getDate("DOB"));
                st.setEmail(rs.getString("EMAIL"));
                studentServices.saveStudent(st);
            }
            return studentServices;
        }catch (SQLException |ClassNotFoundException e){
            System.out.println("khong thanh cong!!");
        }
        return studentServices;
    }
    @Autowired
    StudentServices studentServices = null;

    public void setStudentServices(StudentServices studentServices) {
        this.studentServices = studentServices;
    }
    public StudentServices getStudentServices() {
        setStudentServices(getData());
        return this.studentServices;
    }


    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable String studentId) {
        Student st = getStudentServices().getStudent(studentId).orElseThrow(()->new ResourceNotFoundException("Not found Student with id = " + studentId));
        if (st == null) {
            throw new ResourceNotFoundException("Not found Student with id = " + studentId);
        }
        return new ResponseEntity<>(st, HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<Student> getListStudent() {
        return getStudentServices().getAllStudent();
    }

    @PostMapping(value = "/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> newStudent( @RequestBody Student student) {
        return ResponseEntity.ok(getStudentServices().saveStudent(student));
    }

    @PutMapping("/edit")
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student stu = getStudentServices()
                .getStudent(student.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Already existed Student with id = " + student.getId()));
        List<Student> list = getStudentServices().getAllStudent();
        for (Student s:list) {
            if(s.getEmail().equals(student.getEmail())){
                throw new ResourceNotFoundException("Student with email '"+s.getEmail()+"' already existed");
            }
        }
        return ResponseEntity.ok(getStudentServices().updateStudent(student));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable String id) {
        return ResponseEntity.ok(getStudentServices().deleteStudent(id));
    }

    @GetMapping("/studentp")
    public ResponseEntity<Map<String, Object>> getAllStudent(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {

        try {
            List<Student> list = new ArrayList<Student>();
            Pageable paging = PageRequest.of(page, size);
            Page<Student> pageTuts;
            pageTuts = (Page<Student>) studentServices.getAllStudent();
            list = pageTuts.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("student", list);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
