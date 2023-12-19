/**
 *  핵심 비즈니스 로직은 여기서부터 출발
 */
package com.joeun.test.service;

import java.util.List;

import com.joeun.test.dto.Board;

public interface BoardService {
	
	// 주요 기능들만 추상 메소드로 아래와 같이 작성한다.
	// 구현은 BoardServiceImple 클래스에서.
	
	// 게시글 목록
	public List<Board> list() throws Exception;
	
	// 게시글 조회
	public Board select(int boardNo);
	
	// 게시글 등록
	public Integer insert(Board board) throws Exception;

	// 게시글 수정
	public Integer update(Board board) throws Exception;
	
	// 게시글 삭제
	public Integer delete(int boardNo) throws Exception;
}
