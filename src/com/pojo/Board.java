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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Board entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "board", catalog = "bbs")
public class Board implements java.io.Serializable {

	// Fields

	private Integer id;
	private Board board;
	private Admin admin;
	private String name;
	private String description;
	private String boardImg;
	private Set<Post> posts = new HashSet<Post>(0);
	private Set<Board> boards = new HashSet<Board>(0);

	// Constructors

	/** default constructor */
	public Board() {
	}

	/** minimal constructor */
	public Board(Admin admin, String name) {
		this.admin = admin;
		this.name = name;
	}

	/** full constructor */
	public Board(Board board, Admin admin, String name, String description,
			String boardImg, Set<Post> posts, Set<Board> boards) {
		this.board = board;
		this.admin = admin;
		this.name = name;
		this.description = description;
		this.boardImg = boardImg;
		this.posts = posts;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	public Board getBoard() {
		return this.board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aid", nullable = false)
	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 50)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "boardImg", length = 100)
	public String getBoardImg() {
		return this.boardImg;
	}

	public void setBoardImg(String boardImg) {
		this.boardImg = boardImg;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "board")
	public Set<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "board")
	public Set<Board> getBoards() {
		return this.boards;
	}

	public void setBoards(Set<Board> boards) {
		this.boards = boards;
	}

}