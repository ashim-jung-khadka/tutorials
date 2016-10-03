package com.github.ashim.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.github.ashim.model.Student;

public interface StudentMapper {

	@Insert("INSERT INTO student(userName, password, firstName," + "lastName, dateOfBirth, emailAddress) VALUES"
			+ "(#{userName},#{password}, #{firstName}, #{lastName}," + "#{dateOfBirth}, #{emailAddress})")
	@Options(useGeneratedKeys = true, keyProperty = "id", flushCache = true, keyColumn = "id")
	public void insertStudent(Student student);

	public List<Student> getAllStudent();

	public Student getStudent(int id);

	public Student getStudentByUserName(String userName);

}
