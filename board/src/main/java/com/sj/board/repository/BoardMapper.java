package com.sj.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sj.board.dto.BoardDTO;
import com.sj.board.dto.PageInfoDTO;

@Mapper
public interface BoardMapper {

	
	public List<BoardDTO> selectList(PageInfoDTO pageInfo);

	public void insertBoard(BoardDTO board);

	public BoardDTO selectById(@Param("number")long number);

	public void boardCount(@Param("number")long number);

	public void updateById(BoardDTO board);

	public int deleteById(long number);

	public int deleteBoard();

	public int selectCount();
}
