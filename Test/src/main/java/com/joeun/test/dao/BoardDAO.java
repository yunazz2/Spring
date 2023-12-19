// 이 파일 아이콘에 S가 붙는 이유는 bean으로 등록되어있기 때문에. 
package com.joeun.test.dao;

import java.util.ArrayList;
import java.util.List;

import com.joeun.test.dto.Board;

public class BoardDAO {
	
	public List<Board> list() {
		List<Board> boardList = new ArrayList<Board>();
		
		boardList.add(new Board("제목1", "작성자1", "내용1"));
		boardList.add(new Board("제목2", "작성자2", "내용2"));
		boardList.add(new Board("제목3", "작성자3", "내용3"));
		
		return boardList;
	}

}
