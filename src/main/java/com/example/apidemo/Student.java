package com.example.apidemo;

import org.springframework.context.annotation.Profile;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;


@Entity
@Profile("prod")
@Table(name = "STUDENT")
public class Student extends Bar{
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EMAIL")
    @Email(regexp=".*@.*\\..*", message = "Email should be valid")
    @NotBlank
    private String email;

    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dayofbirth;

    public Student() {
    }

    public Student(String id, String name, String address, String email, Date dayofbirth) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.dayofbirth = dayofbirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDayofbirth() {
        return dayofbirth;
    }

    public void setDayofbirth(Date dayofbirth) {
        this.dayofbirth = dayofbirth;
    }
}
