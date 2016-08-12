package com.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dao.BaseDao;
import com.pojo.Student;
import com.service.IStudentService;


@Component("studentService")
public class StudentServiceImpl implements IStudentService{

	@Resource(name="dao")
	BaseDao dao;

	@Override
	public Student getStudentByStuNum(String stuNum) {
		Student s = (Student) dao.loadObject("from Student as s where s.stuNum='" + stuNum + "' ");
		if(s != null)
			return s;
		return null;
	}

	@Override
	public boolean modifyStudent(Student student) {
		try{
			dao.saveOrUpdate(student);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
