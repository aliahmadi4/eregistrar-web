package com.ali.eregistrar.repository;

import org.springframework.data.repository.CrudRepository;

import com.ali.eregistrar.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

	
}
