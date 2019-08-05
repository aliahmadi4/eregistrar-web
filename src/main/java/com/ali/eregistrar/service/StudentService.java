package com.ali.eregistrar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ali.eregistrar.model.Student;
import com.ali.eregistrar.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository repository;
	
	public void saveStudent(Student student) {
		repository.save(student);
	}
	
	public Iterable<Student> getAllStudent(){
		return repository.findAll();
	}
	
	public void deleteStudent(Long id) {
		repository.deleteById(id);
	}
	
	public Student getStudentById(Long id) {
		return repository.findById(id).orElse(null);
		
	}
}
