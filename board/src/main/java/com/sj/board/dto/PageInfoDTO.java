package com.sj.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageInfoDTO {

	private int pageNo; /*현재 페이지*/
	private int totalCount; /*게시물 숫자*/
	private int limit; /*한 페이지에서 보여줄 게시물 수*/
	private int buttonAmount; /*페이징 버튼 숫자*/
	private int maxPage; /*최대 페이징 수*/
	private int startPage; /*시작 페이징 값*/
	private int endPage; /*끝 페이징 값*/
	private int startRow; /*조회할 시작 row 값*/
	private int endRow; /*조회할 끝 row 값*/
}
