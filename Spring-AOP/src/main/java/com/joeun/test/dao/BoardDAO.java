package com.joeun.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.joeun.test.dto.Board;

@Repository // 데이터 액세스 객체로 빈을 등록하겠다.
public class BoardDAO {
	
	/**
	 * 게시글 목록 조회
	 * @return
	 */
	public List<Board> list() {
		List<Board> boardList = new ArrayList<Board>();
		
		boardList.add(new Board("제목1", "작성자1", "내용1"));
		boardList.add(new Board("제목2", "작성자2", "내용2"));
		boardList.add(new Board("제목3", "작성자3", "내용3"));
		
		return boardList;
	}
	
	
	/**
	 * 게시글 조회
	 * @return
	 */
	public Board select(int boardNo) {
		Board board = new Board("제목", "작성자", "내용");
//		Board board = null;
		
		return board;
	}

	
	/**
	 * 게시글 등록
	 * @return
	 */
	public int insert(Board board) {
		int result = 0;
		return result;
	}
	
	
	/**
	 * 게시글 수정
	 * @return
	 */
	public int update(Board board) {
		int result = 0;
		return result;
	}
	
	
	/**
	 * 게시글 삭제
	 * @return
	 */
	public int delete(int boardNo) {
		int result = 0;
		return result;
	}
}
