package com.github.ashim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.ashim.mappers.StudentMapper;
import com.github.ashim.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;

	@Override
	public List<Student> getAllStudent() {
		return studentMapper.getAllStudent();
	}

	@Override
	public Student getStudent(Integer id) {
		return studentMapper.getStudent(id);
	}

	@Override
	@Transactional
	public Student insertStudent(Student student) {
		studentMapper.insertStudent(student);
		return student;
	}

	@Override
	public Student getStudentByUserName(String userName) {
		return studentMapper.getStudentByUserName(userName);
	}

}