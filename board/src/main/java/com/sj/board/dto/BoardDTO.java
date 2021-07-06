package com.sj.board.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO implements java.io.Serializable{

	private long number;
	private String title;
	private String content;
	private String writer;
	private int cnt;
	private int boardLevel;
	private int reply;
	private String status;
	private Date modifyDate;
	
	
}
