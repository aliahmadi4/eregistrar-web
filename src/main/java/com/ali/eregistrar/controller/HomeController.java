package com.ali.eregistrar.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ali.eregistrar.model.Student;
import com.ali.eregistrar.service.StudentService;

@Controller
public class HomeController {

	@Autowired
	StudentService service;
	
	@GetMapping(value= {"/", "/eregistrar/home","/home"})
	public String showHome() {
		return "index.html";
	}
	
	@GetMapping({"/eregistrar/students"})
	public ModelAndView showStudents(@RequestParam(defaultValue= "0") int pageno) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("students", service.getStudentsPaged(pageno));
		mv.addObject("currentPage", pageno);
		mv.setViewName("studentsList.html");
		return mv;
	}
	
	@GetMapping("/eregistrar/addStudent")
	public String addNewStudent(Model model) {
		model.addAttribute("student", new Student());
		return "newStudent.html";
	}
	
	@PostMapping("/eregistrar/addStudent")
	public String saveNewStudent(@ModelAttribute("student") Student student) {
		service.saveStudent(student);
		//System.out.println(student);
		return "redirect:/eregistrar/students";
	}
	
	@GetMapping("/eregistrar/edit/{id}")
	public String editStudent(@PathVariable Long  id, Model model) {
		Student st = service.getStudentById(id);
		if(st!=null) {
			model.addAttribute("student", st);
			return "editStudent.html";
		}
		
		return "editStudent.html";
	}
	
	@PutMapping("/eregistrar/update/")
	public String updateStudent(@ModelAttribute("student") Student student) {
		service.saveStudent(student);
		return "redirect:/eregistrar/students";
	}
	
	@GetMapping("/eregistrar/remove/{Id}")
	public String deleteStudent(@PathVariable("Id") Long id) {
		service.deleteStudent(id);
		return "redirect:/eregistrar/students";
	}
	
	@GetMapping("/eregistrar/students/search")
	public ModelAndView searchStudent(@RequestParam String searchText, @RequestParam(defaultValue= "0") int pageno) {
//		List<Student> list = new ArrayList<>();
//		list.add(service.getStudentById(Long.parseLong(searchText)));
		//Page<Student> students = ;
		//students.forEach(System.out::println);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("students", service.getStudentByStudentNumber(searchText, pageno));
		mv.addObject("currentPage", pageno);
		mv.addObject("searchText", searchText);
		mv.setViewName("searchResult.html");
		return mv;
	}
	
}
