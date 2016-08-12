package com.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pojo.Board;
import com.pojo.Post;
import com.pojo.Student;
import com.service.IBoardService;
import com.service.IPostService;
import com.service.IStudentService;
import com.serviceImpl.BoardServiceImpl;
import com.serviceImpl.PostServiceImpl;
import com.serviceImpl.StudentServiceImpl;


public class IndexAction extends ActionSupport{
	@Resource(name="studentService")
	IStudentService studentService;
	
	@Resource(name="boardService")
	IBoardService boardService;
	
	@Resource(name="postService")
	IPostService postService;

	
	private List<Board>rootBoard;
	private int todayNum;
	private int yestNum;
	private int highestNum;
	private int total;
	private Student student;
	private List<Post> hotPosts;
	public int getTodayNum() {
		return todayNum;
	}
	public void setTodayNum(int todayNum) {
		this.todayNum = todayNum;
	}
	public int getYestNum() {
		return yestNum;
	}
	public void setYestNum(int yestNum) {
		this.yestNum = yestNum;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Post> getHotPosts() {
		return hotPosts;
	}
	public void setHotPosts(List<Post> hotPosts) {
		this.hotPosts = hotPosts;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public List<Board> getRootBoard() {
		return rootBoard;
	}
	public void setRootBoard(List<Board> rootBoard) {
		this.rootBoard = rootBoard;
	}

	@Override
	public String execute() throws Exception {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		studentService = (StudentServiceImpl)context.getBean("studentService");
		boardService = (BoardServiceImpl)context.getBean("boardService");
		postService = (PostServiceImpl)context.getBean("postService");
		
		try{
			Student sessionStudent = (Student)ActionContext.getContext().getSession().get("student");
			if(sessionStudent != null){
				setStudent(studentService.getStudentByStuNum(sessionStudent.getStuNum()));
			}
			setRootBoard(boardService.loadRootBoards());
			setHotPosts(postService.rankPosts(10));
			setTotal(postService.countTotalPost());
			setYestNum(postService.countYesteradyPost());
			setTodayNum(postService.countTodayPost());
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
}
