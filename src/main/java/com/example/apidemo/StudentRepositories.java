package com.example.apidemo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("StudentRepositories")
@Profile("prod")
public interface StudentRepositories extends JpaRepository<Student, String> {

//    public Optional<Student> findId(String id);
//    public List<Student> findListId(UUID list);


}
