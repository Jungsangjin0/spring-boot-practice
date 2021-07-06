package com.sj.board.service;

import java.util.List;

import com.sj.board.dto.BoardDTO;
import com.sj.board.dto.PageInfoDTO;

public interface BoardService {

	/*select board list*/
	public List<BoardDTO> selectList(PageInfoDTO pageInfo);
	
	/*insert board*/
	public void insertBoard(BoardDTO board);
	
	/*select board by id*/
	public BoardDTO selectById(long number);

	/*update board by id*/
	public void updateById(BoardDTO board);

	/*delete board by id*/
	public int deleteById(long number);

	/*select count(*)*/
	public int selectCount();

	/*board count*/
	public void boardCount(long number);
}
