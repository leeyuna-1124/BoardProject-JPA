package com.study.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.board.dto.BoardRequestDto;
import com.study.board.dto.BoardResponseDto;
import com.study.board.service.BoardService;
import com.study.exception.CustomException;
import com.study.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	//Exception Test
	@GetMapping("/test")
	public String test() {
		throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
	}
	
	/*
	 * 게시글 생성 
	 */
	 @PostMapping("/boards")
	 public Long save(@RequestBody final BoardRequestDto params) {
		 return boardService.save(params);
	 }
	 
	 /*
	  * 게시글 리스트 조회 
	  */
	 @GetMapping("/boards")
	 public List<BoardResponseDto> findAll(){
		 return boardService.findAll();
	 }
	 
	 /*
	  * 게시글 수
	  */
	 @PatchMapping("/boards/{id}")
	 public Long save(@PathVariable final Long id, @RequestBody final BoardRequestDto params) {
		 return boardService.update(id, params);
	 }
}
