package com.sj.board;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sj.board.repository.BoardMapper;

@Component
public class ScheduledTest {

	private final BoardMapper mapper;
	
	@Autowired
	public ScheduledTest(BoardMapper mapper) {
		this.mapper = mapper;
	}
	
	@Scheduled(cron = "0 0 0 1 * *")
	public void test() {
		System.out.println(new Date(System.currentTimeMillis()));
		int result = mapper.deleteBoard();
		System.out.println(result);
	}
}
