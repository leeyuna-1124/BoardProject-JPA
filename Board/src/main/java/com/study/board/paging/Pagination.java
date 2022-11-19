package com.study.board.paging;

import lombok.Getter;

@Getter
public class Pagination {
	
	private int totalRecordCount;    //전체 데이터 수
	private int totalPageCount;	     //전체 페이지 수
	private int startPage;           //첫 페이지 번호
	private int endPage;             //끝 페이지 번호
	private int limitStart;          //limit 시작 위치
	private boolean existPrevPage;   //이전 페이지 존재 여부
	private boolean existNextPage;   //다음 페이지 존재 여부
	
	public Pagination(int totalRecordCount, CommonParams params) {
		if(totalRecordCount > 0) {
			this.totalRecordCount = totalRecordCount;
			this.calculation(params);
		}
	}
	
	private void calculation(CommonParams params) {
		
		totalPageCount = ((totalRecordCount - 1) / params.getRecordPerPage()) + 1;
		
		if(params.getPage() > totalPageCount) {
			params.setPage(totalPageCount);
		}
		
		startPage = ((params.getPage() - 1) / params.getPageSize()) * params.getPageSize() + 1;
		
		endPage = startPage + params.getPageSize() -1 ;
		
		if(endPage > totalPageCount) {
			endPage = totalPageCount;
		}
		
		limitStart = (params.getPage() - 1) * params.getRecordPerPage();
		
		existPrevPage = startPage != 1;
		
		existNextPage = (endPage * params.getRecordPerPage()) < totalRecordCount;
	}
}
