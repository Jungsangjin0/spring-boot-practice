package com.sj.board.paging;

import com.sj.board.dto.PageInfoDTO;

public class Pagenation {

	public static PageInfoDTO getPageInfo(int pageNo, int totalCount) {
		
		int maxPage;
		int startPage;
		int endPage;
		int startRow;
		int endRow;
		int limit = 10;
		int buttonAmount = 10;
		
		maxPage = (int) Math.ceil((double)totalCount / limit);
		
		if(pageNo >= maxPage) {
			pageNo = maxPage;
		}
		startPage = (int) (Math.ceil((double)pageNo / buttonAmount) - 1) * buttonAmount + 1;
		
		endPage = startPage + buttonAmount - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		if(maxPage == 0 && endPage == 0) {
			maxPage = startPage;
			endPage = startPage;
		}
		
		startRow = (pageNo - 1) * limit + 1;
		endRow = startRow + limit - 1;
		
		return PageInfoDTO.builder()
						  .pageNo(pageNo)
						  .totalCount(totalCount)
						  .limit(limit)
						  .buttonAmount(buttonAmount)
						  .maxPage(maxPage)
						  .startPage(startPage)
						  .endPage(endPage)
						  .startRow(startRow)
						  .endRow(endRow)
						  .build();
	}

}
