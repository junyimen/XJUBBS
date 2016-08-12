package com.service;

import java.util.List;

import com.pojo.Board;


public interface IBoardService {
	public Board loadBoard(int id);
	public List<Board> loadChildBoards(int parentId);
	public List<Board> loadAllBoards();
	public List<Board> loadRootBoards();
	public boolean saveOrUpdateBoard(Board board);
}
