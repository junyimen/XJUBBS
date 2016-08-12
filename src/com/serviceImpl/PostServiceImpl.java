package com.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.pojo.Admin;
import com.pojo.Post;
import com.pojo.Student;
import com.service.IPostService;


@Component("postService")
public class PostServiceImpl implements IPostService{
	@Resource(name="dao")
	BaseDao dao;
	
	@Override
	public boolean saveOrUpdate(Post post) {
		try{
			dao.saveOrUpdate(post);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Post> allPost() {
		List<Post> list = dao.listAll("Post");
		return list;
	}
	
	@Override
	public Post loadPost(int id) {
		return (Post) dao.loadById(Post.class, new Integer(id));
	}

	@Override
	public List<Post> allPostsByUser(Object user) {
		if(user instanceof Student){
			Student s = (Student)user;
			List<Post> list = (List<Post>) dao.query("from Post as p where p.student.id=" + s.getId() + " order by p.publishTime desc");
			return list;
		}
		else if(user instanceof Admin){
			Admin a = (Admin)user;
			List<Post> list = (List<Post>) dao.query("from Post as p where p.admin.id=" + a.getId() + " order by p.publishTime desc");
			return list;
		}
		else
			return null;
	}

	@Override
	public boolean deletePost(int id) {
		try{
			dao.delById(Post.class, new Integer(id));
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@Override
	public List<Post> pageAllPost(int bid, int pageNo, int pageSize) {
		return dao.query("from Post as p where p.admin.id='" + bid + "' order by p.publishTime desc ", pageNo, pageSize);
	}

	@Override
	public int getPostsCount() {
		return dao.countAll("Post");
	}
	
	/**
	 * 
	 */
	@Override
	public List<Post> searchPosts(String searchKey) {
		return dao.query("from Post as p where p.name like '%" + searchKey + "%'");
	}

	@Override
	public List<Post> rankPosts(int size) {
		List<Post> list = dao.query("from Post as p order by p.count desc");
		List<Post> result = new ArrayList<Post>();
		for(int i = 0; i < list.size(); i++){
			result.add(list.get(i));
		}
		return result;
	}

	@Override
	public int countTotalPost() {
		return dao.countAll("Post");
	}

	@Override
	public int countYesteradyPost() {
		Date todayDate = new Date();
		Date yesterDate = new Date(System.currentTimeMillis() - 1000*60*60*60*24);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sf.format(todayDate) + "00:00:00";
		String yesterday = sf.format(yesterDate) + "00:00:00";
		return dao.countQuery("select count(*) from Post as p where p.publishTime between'" + yesterday + "' and '" + today + "' ");
		
		
	}

	@Override
	public int countDayLargestPost() {
		throw new UnsupportedOperationException("Not supported countDayLargestPost");
	}

	@Override
	public int countTodayPost() {
		Date todayDate = new Date();
		Date tomorrowDate = new Date(System.currentTimeMillis() + 1000*60*60*24);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sf.format(todayDate);
		String tomorrow = sf.format(tomorrowDate);
		return dao.countQuery("select count(*) from Post as p where p.publishTime between '" + today + "' and '" + tomorrow + "' ");
		
	}
	
}
