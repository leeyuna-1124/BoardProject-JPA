package com.study.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
