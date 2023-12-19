package com.joeun.test.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joeun.test.HomeController;
import com.joeun.test.dao.BoardDAO;
import com.joeun.test.dto.Board;

@Service // 비즈니스 로직을 처리하는 서비스 클래스로 빈을 등록하겠다.	
public class BoardServiceImpl implements BoardService {
	
	// 로그를 찍어보는 내용(HomeController에서 가지고 옴)
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired
	private BoardDAO boardDAO;
	
	// 구현하려면 BoardService에 추상 메소드로 만들어두었던 걸 오버라이딩 해야겠죠?
	@Override
	public List<Board> list() throws Exception {
		// logger.info("게시글 목록을 조회합니다.");
		
		List<Board> boardList = boardDAO.list();
		
		// logger.info("info");
		// logger.error("error");
		// logger.warn("warn");
		
		int count = boardList.size();
		// logger.info("boardList 요소 개수 : " + count);
		
		// logger.info("게시글 목록을 반환합니다.");
		return boardList;
	}

	@Override
	public Board select(int boardNo)  {
		Board board = boardDAO.select(boardNo);
		String title = board.getTitle();
		return board;
	}

	@Override
	public Integer insert(Board board) throws Exception {
		int result = boardDAO.insert(board);
		return result;
	}

	@Override
	public Integer update(Board board) throws Exception {
		int result = boardDAO.update(board);
		return result;
	}

	@Override
	public Integer delete(int boardNo) throws Exception {
		int result = boardDAO.delete(boardNo);
		return result;
	}

}
