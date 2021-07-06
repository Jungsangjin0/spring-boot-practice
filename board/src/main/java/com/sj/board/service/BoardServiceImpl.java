package com.sj.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sj.board.dto.BoardDTO;
import com.sj.board.dto.PageInfoDTO;
import com.sj.board.repository.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements  BoardService{

	private final BoardMapper mapper;
	
	@Autowired
	public BoardServiceImpl(BoardMapper mapper) {
		this.mapper = mapper;
		
	}
	
	/*select board list*/
	@Override
	public List<BoardDTO> selectList(PageInfoDTO pageInfo) {
		
		return mapper.selectList(pageInfo);
	}


	/*insert board */
	@Override
	public void insertBoard(BoardDTO board) {
		mapper.insertBoard(board);
	}

	@Override
	public BoardDTO selectById(long number) {
		
		
		return mapper.selectById(number);
	}

	@Override
	public void updateById(BoardDTO board) {
		
		mapper.updateById(board);
		
	}

	@Override
	public int deleteById(long number) {
		System.out.println("==========================");
		System.out.println("number : " + number);
		System.out.println("==========================");
		
		return mapper.deleteById(number);
	}

	@Override
	public int selectCount() {
		
		return mapper.selectCount();
	}

	@Override
	public void boardCount(long number) {
		
		mapper.boardCount(number);
	}
	
}
