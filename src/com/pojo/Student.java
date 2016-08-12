package com.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student", catalog = "bbs")
public class Student implements java.io.Serializable {

	// Fields

	private Integer id;
	private String stuNum;
	private String realName;
	private String nickName;
	private String password;
	private String qq;
	private String email;
	private String major;
	private String className;
	private String photoPath;
	private Set<Reply> replies = new HashSet<Reply>(0);
	private Set<Post> posts = new HashSet<Post>(0);

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(String stuNum, String password) {
		this.stuNum = stuNum;
		this.password = password;
	}

	/** full constructor */
	public Student(String stuNum, String realName, String nickName,
			String password, String qq, String email, String major,
			String className, String photoPath, Set<Reply> replies,
			Set<Post> posts) {
		this.stuNum = stuNum;
		this.realName = realName;
		this.nickName = nickName;
		this.password = password;
		this.qq = qq;
		this.email = email;
		this.major = major;
		this.className = className;
		this.photoPath = photoPath;
		this.replies = replies;
		this.posts = posts;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "stuNum", nullable = false, length = 15)
	public String getStuNum() {
		return this.stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

	@Column(name = "realName", length = 10)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "nickName", length = 20)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "password", nullable = false, length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "qq", length = 20)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "email", length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "major", length = 20)
	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "className", length = 20)
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name = "photoPath", length = 100)
	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
	public Set<Reply> getReplies() {
		return this.replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
	public Set<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

}