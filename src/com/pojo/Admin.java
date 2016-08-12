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
 * Admin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "admin", catalog = "bbs")
public class Admin implements java.io.Serializable {

	// Fields

	private Integer id;
	private String account;
	private String password;
	private Integer qx;
	private String nickName;
	private String name;
	private String photoPath;
	private Set<Post> posts = new HashSet<Post>(0);
	private Set<Reply> replies = new HashSet<Reply>(0);
	private Set<Board> boards = new HashSet<Board>(0);

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** minimal constructor */
	public Admin(String account, String password, Integer qx) {
		this.account = account;
		this.password = password;
		this.qx = qx;
	}

	/** full constructor */
	public Admin(String account, String password, Integer qx, String nickName,
			String name, String photoPath, Set<Post> posts, Set<Reply> replies,
			Set<Board> boards) {
		this.account = account;
		this.password = password;
		this.qx = qx;
		this.nickName = nickName;
		this.name = name;
		this.photoPath = photoPath;
		this.posts = posts;
		this.replies = replies;
		this.boards = boards;
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

	@Column(name = "account", nullable = false, length = 10)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "password", nullable = false, length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "qx", nullable = false)
	public Integer getQx() {
		return this.qx;
	}

	public void setQx(Integer qx) {
		this.qx = qx;
	}

	@Column(name = "nickName", length = 10)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "name", length = 10)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "photoPath", length = 100)
	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "admin")
	public Set<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "admin")
	public Set<Reply> getReplies() {
		return this.replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "admin")
	public Set<Board> getBoards() {
		return this.boards;
	}

	public void setBoards(Set<Board> boards) {
		this.boards = boards;
	}

}