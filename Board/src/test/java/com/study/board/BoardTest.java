package com.study.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;

@SpringBootTest
public class BoardTest {
	
	@Autowired
	BoardRepository boardRepository;
	
	@Test
	void save() {
		
		Board params = Board.builder()
				.title("첫번째 게시글 제목")
				.content("첫번째 게시글 내용")
				.writer("홍길동")
				.hits(0)
				.deleteYn('N')
				.build();
		
		//게시글 저장
		boardRepository.save(params);
		
		//게시글 정보 조회
		Board entity = boardRepository.findById(1L).get();
		assertThat(entity.getTitle()).isEqualTo("첫번째 게시글 제목");
		assertThat(entity.getContent()).isEqualTo("첫번째 게시글 내용");
		assertThat(entity.getWriter()).isEqualTo("홍길동");
		
	}
	
	@Test
	void findAll() {
		
		//전체 게시글 수 조회
		long boardCount = boardRepository.count();
		
		//전체 게시글 리스트 조회
		List<Board> boards = boardRepository.findAll();
	}
	
	@Test
	void delete() {
		
		//게시글 조회
		Board entity = boardRepository.findById(1L).get();
		
		//게시글 삭제
		boardRepository.delete(entity);
	}

}
