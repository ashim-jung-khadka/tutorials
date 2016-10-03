package com.github.ashim.service;

import java.util.List;

import com.github.ashim.model.Student;

public interface StudentService {

	public List<Student> getAllStudent();

	public Student getStudent(Integer id);

	public Student insertStudent(Student student);

	public Student getStudentByUserName(String userName);

}