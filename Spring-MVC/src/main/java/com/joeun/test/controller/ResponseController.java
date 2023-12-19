package com.joeun.test.controller;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.joeun.test.dto.Board;
import com.joeun.test.service.BoardService;



/**
 * 컨트롤러 응답
 * @author LEEYUNA
 *
 */
@Controller
@RequestMapping("/response")
public class ResponseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ResponseController.class);
	
	@Autowired // 의존성 주입
	private BoardService boardService;
	
	
	// void : 요청 경로와 같은 경로의 view 응답한다.
	@RequestMapping("/index")
	public void response() {
		
		logger.info("void 타입 - /response/index");
		logger.info("/response/index.jsp 뷰를 응답");

	}
	
	
	// String 뷰 이름을 지정하여 응답한다.
	/**
	 * 요청 경로 	: /response/view
	 * 응답		: /response/index.jsp
	 * => view라고 요청이 왔어도 index를 응답한다.
	 * @return
	 */
	@RequestMapping("/view")
	public String responseView() {
		
		logger.info("String 타입 - /response/index");
		logger.info("/response/index.jsp 뷰를 응답");
		logger.info("view 이름을 반환값으로 지정");

		
		return "response/index";
	}

	
	
	@RequestMapping("/model/view")
	public ModelAndView responseModelAndView() {
		
		logger.info("ModelAndView 타입 - /response/model/view");
		logger.info("/response/index.jsp 뷰를 응답");
		logger.info("모델과 뷰를 ModelAndView 객체에 지정하여 응답");
		
		ModelAndView mv = new ModelAndView();
		
		// 뷰 이름 지정
		mv.setViewName("/response/view");				// view : /response/index.jsp
		
		// 모델 등록
		Board board = new Board("제목", "작성자", "내용");
		mv.addObject("board", board);
		mv.addObject("message", "ModelAndView 컨트롤러 응답...");
														// model : board, message
		return mv;
	}
	
	
	
	/**
	 * @ResponseBody : 응답하는 데이터를 응답 메시지의 body(본문)에 지정하는 어노테이션
	 * @return
	 * 
	 */
	@ResponseBody
	@RequestMapping("/data/board")
	public Board responseBoard() {
		
		Board board = new Board("제목", "작성자", "내용");
		
		return board;
	}

	
	/**
	 * 브라우저에서 Accept 헤더의 값이 우선순위
	 * - text/html
	 * - application/xhtml+xml
	 * - application/xml
	 * - ...
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	// @RequestMapping("/data/board/list")
	@RequestMapping(value = "/data/board/list", produces = "application/json")
	public List<Board> responseBoardList() throws Exception {
		// produces = "application/json"
		// - Accept:application/xml 으로 요청이 와도, json 으로 응답할 수 있다.
		List<Board> boardList = boardService.list();
		return boardList;
	}

	
	
	@ResponseBody
	@RequestMapping(value = "/data/map", produces = "application/json")
	public Map<String, Board> responseMap() throws Exception {
		
		Map<String, Board> map = new HashMap<String, Board>();
		map.put("board1", new Board("제목1", "작성자1", "내용1"));
		map.put("board2", new Board("제목2", "작성자2", "내용2"));
		map.put("board3", new Board("제목3", "작성자3", "내용3"));
		
		return map;
	}

	
	
	@ResponseBody
	@RequestMapping("/data/entity/void")
	public ResponseEntity<Void> responseEntityVoid() {
		
		// ResponseEntity
		// : 스프링 프레임워크에서 응답 헤더, 본문, 상태 코드 등을 캡술화하여 사용하는 객체
		
		// ResponseEntity<Void>
		// : 헤더 정보, 상태 코드만 지정하여 사용할 수 있다.
		
		// HttpStatus 열거타입
		// : 상태 코드를 가지고있는 스프링 프레임 워크의 열거 타입
		// - 200 : OK
		// - 404 : NOT_FOUND 
		// - 500 : INTERNAL_SERVER_ERROR 
		
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	
	
	@ResponseBody
	@RequestMapping("/data/entity/string")
	public ResponseEntity<String> responseEntityString() {
		
		// new ResponseEntity<반환타입>(응답메시지, 상태코드);
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/data/entity/board")
	public ResponseEntity<Board> responseEntityBoard() {
		Board board = new Board("제목","작성자","내용");
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/data/entity/board/list")
	public ResponseEntity<List<Board>> responseEntityBoardList() throws Exception {
		List<Board> boardList = boardService.list();
		// return new ResponseEntity<>(boardList, HttpStatus.OK);		// <> 생략가능
		return new ResponseEntity<List<Board>>(boardList, HttpStatus.OK);
	}
	
	
	
	@ResponseBody
	@RequestMapping("/data/entity/map")
	public ResponseEntity<Map<String, Board>> responseEntityBoardMap() throws Exception {
		
		List<Board> boardList = boardService.list();
		Map<String, Board> map = new HashMap<String, Board>();
		
		int i = 1;
		for (Board board : boardList) {
			map.put("board" + i++, board);
		}
				
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	
	
	/**
	 * 파일 다운로드
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/data/file")
	public ResponseEntity<byte[]> responseFile() throws Exception {
		
		// 파일 경로
		String filePath = "D:\\LYN\\UPLOAD\\slide04.jpg";
		String fileName = "test.jpg";
		
		// 헤더정보
		HttpHeaders headers = new HttpHeaders();
		// headers.setContentType( MediaType.IMAGE_JPEG );					// 이미지로 응답
		headers.setContentType( MediaType.APPLICATION_OCTET_STREAM);		// 다운로드로 응답
		
		
		// headers.add("헤더명", "값")
		// Content-Disposition
		// - inline		: 웹 페이지에서 출력(기본 값)
		// - attachment	: 첨부파일 (다운로드)
		headers.add("Content-Disposition", "attachment; filename=" + fileName);	// 다운로드 여부, 파일명 지정
		
		byte[] fileData = null;
		try {
			FileInputStream fis = new FileInputStream(filePath);
			
			// commons-io 라이브러리
			// toByteArray() : 파일을 바이트코드로 변환
			fileData = IOUtils.toByteArray(fis);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		// new ResponseEntity<반환타입>(응답메시지, 헤더,상태코드);
		return new ResponseEntity<byte[]>(fileData, headers, HttpStatus.OK);
	}
	
	

}

