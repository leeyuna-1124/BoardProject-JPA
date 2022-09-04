package com.study.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//기본 생성자를 생성해줌 -> 동일한 패키지 내의 클래스에서만 객체를 생성
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
// cf)앤티티 클래스는 테이블 그 자체이므로 변경되면 안된다! 따라서 @Setter는 선언하지 않음!!
public class Board {
	
	@Id
	//자동증가(auto_increment)를 지원
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //PK
	
	private String title; //제목
	
	private String content; //내용
	
	private String writer; //작성자
	
	private int hits; //조회수
	
	private char deleteYn; //삭제 여부
	
	private LocalDateTime createdDate = LocalDateTime.now(); //생성일
	
	private LocalDateTime modifiedDate; //수정일
	
	//롬복에서 사용하는 어노테이션으로, 생성자를 대신한다고 보면 됨.
	@Builder
	public Board(String title, String content, String writer, int hits, char deleteYn) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.hits = hits;
		this.deleteYn = deleteYn;
	}
}
