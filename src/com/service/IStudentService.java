package com.service;

import com.pojo.Student;


public interface IStudentService {

	public Student getStudentByStuNum(String stuNum);
	public boolean modifyStudent(Student student);

}
