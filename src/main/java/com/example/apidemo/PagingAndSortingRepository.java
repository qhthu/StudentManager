package com.example.apidemo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface PagingAndSortingRepository<Student, ID> extends CrudRepository<Student, ID> {
    Page<Student> findAll(Pageable pageable);
}