package com.ali.eregistrar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.ali.eregistrar.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Page<Student> findAllByStudentNumberContaining(String s, Pageable pageable);
	Page<Student> findAllByStudentNumberContainingOrFirstNameContainingOrLastNameContaining(String s,String s1, String s2, Pageable pageable);
}
