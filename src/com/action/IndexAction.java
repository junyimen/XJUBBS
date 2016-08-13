package com.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller("indexAction")
public class IndexAction extends ActionSupport{
	@Resource(name="studentService")
	private IStudentService studentService;
	@Resource(name="boardService")
	private IBoardService boardService;
	@Resource(name="postService")
	private IPostService postService;
	private List<Board>rootBoard;
	private int todayNum;
	private int yestNum;
	private int highestNum;
	private int total;
	private Student student;
	private List<Post> hotPosts;
	
	@Override
	public String execute() throws Exception {

		try{
			Student sessionStudent = (Student)ActionContext.getContext().getSession().get("student");
			if(sessionStudent != null){
				setStudent(studentService.getStudentByStuNum(sessionStudent.getStuNum()));
			}
			//初始化参数
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

	
	public IBoardService getBoardService() {
		return boardService;
	}
	public int getHighestNum() {
		return highestNum;
	}
	public List<Post> getHotPosts() {
		return hotPosts;
	}
	public IPostService getPostService() {
		return postService;
	}
	public List<Board> getRootBoard() {
		return rootBoard;
	}
	public Student getStudent() {
		return student;
	}
	public IStudentService getStudentService() {
		return studentService;
	}
	public int getTodayNum() {
		return todayNum;
	}
	public int getTotal() {
		return total;
	}
	public int getYestNum() {
		return yestNum;
	}
	public void setBoardService(IBoardService boardService) {
		this.boardService = boardService;
	}
	public void setHighestNum(int highestNum) {
		this.highestNum = highestNum;
	}
	public void setHotPosts(List<Post> hotPosts) {
		this.hotPosts = hotPosts;
	}
	public void setPostService(IPostService postService) {
		this.postService = postService;
	}
	public void setRootBoard(List<Board> rootBoard) {
		this.rootBoard = rootBoard;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}
	public void setTodayNum(int todayNum) {
		this.todayNum = todayNum;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public void setYestNum(int yestNum) {
		this.yestNum = yestNum;
	}
}
