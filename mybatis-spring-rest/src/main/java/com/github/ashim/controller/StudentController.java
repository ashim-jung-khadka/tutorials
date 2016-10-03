package com.github.ashim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.ashim.model.Student;
import com.github.ashim.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Student> getAllStudent() {
		return studentService.getAllStudent();
	}

	@RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
	public Student getStudent(@PathVariable Integer studentId) {
		return studentService.getStudent(studentId);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Student getStudentByUserName(@RequestParam("userName") String userName) {
		return studentService.getStudentByUserName(userName);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Student create(@RequestBody Student student) {
		return studentService.insertStudent(student);
	}

}
