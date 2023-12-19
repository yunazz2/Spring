// 이 파일에 S가 붙는 이유는 @Service를 명시하여 빈 등록이 되었기때문에
package com.joeun.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joeun.test.dao.BoardDAO;
import com.joeun.test.dto.Board;

@Service
public class BoardService {
	
	// root-context에서 bean으로 정의해두었고, 그걸 Autowired를 붙이면서 의존성 자동 주입을 통해 사용
	@Autowired
	private BoardDAO boardDAO;
	
	public List<Board> list() {
		
		return boardDAO.list();
	}
	
}
