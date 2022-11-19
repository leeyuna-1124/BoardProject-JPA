package com.study.board.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.study.board.dto.BoardRequestDto;
import com.study.board.dto.BoardResponseDto;
import com.study.board.entity.Board;
import com.study.board.model.BoardMapper;
import com.study.board.paging.CommonParams;
import com.study.board.paging.Pagination;
import com.study.board.repository.BoardRepository;
import com.study.exception.CustomException;
import com.study.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	private final BoardMapper boardMapper;
	
	/*
	 * 게시글 생성 
	 */
	@Transactional
	public Long save(final BoardRequestDto params) {
		
		Board entity = boardRepository.save(params.toEntity());
		return entity.getId();
	}
	
	/*
	 * 게시글 리스트 조회 
	 */
	public List<BoardResponseDto> findAll(){
		
		Sort sort = Sort.by(Direction.DESC, "id", "createdDate");
		List<Board> list = boardRepository.findAll(sort);
		return list.stream().map(BoardResponseDto::new).collect(Collectors.toList()); //엔티티를 dto타입으로 변경해서 리턴 
	}
	
	/*
	 * 게시글 수정 
	 */
	@Transactional
	public Long update(final Long id, final BoardRequestDto params) {
		
		Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
		entity.update(params.getTitle(), params.getContent(), params.getWriter());
		
		return id;
	}
	
	/*
	 * 게시글 삭제 
	 */
	@Transactional
	public Long delete(final Long id) {
		
		Board entity = boardRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.POSTS_NOT_FOUND));
		entity.delete();
		return id;
	}
	
	/*
	 * 게시글 상세정보 조회 
	 */
	@Transactional
	public BoardResponseDto findById(final Long id) {
		
		Board entity = boardRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.POSTS_NOT_FOUND));
		entity.increaseHits();
		return new BoardResponseDto(entity);
	}
	
	/*
	 * 게시글 리스트 조회 - (삭제 여부 기준) 
	 */
	public List<BoardResponseDto> findAllByDeleteYn(final char deleteYn){
		
		Sort sort = Sort.by(Direction.DESC, "id", "createdDate");
		List<Board> list = boardRepository.findAllByDeleteYn(deleteYn, sort);
		return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
	}
	
	/*
	 * 게시글 리스트 조회 - 페이징
	 */
	public Map<String, Object> findAll(CommonParams params){
		
		int count = boardMapper.count(params);
		
		if(count < 1) {
			return Collections.emptyMap();
		}
		
		Pagination pagination = new Pagination(count, params);
		params.setPagination(pagination);
		
		List<BoardResponseDto> list = boardMapper.findAll(params);
		
		Map<String, Object> response = new HashMap<>();
		response.put("params", params);
		response.put("list", list);
		return response;
		
	}

}
