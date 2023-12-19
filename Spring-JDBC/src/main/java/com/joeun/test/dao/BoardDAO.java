package com.joeun.test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.joeun.test.dto.Board;

@Repository // 데이터 액세스 객체로 빈을 등록하겠다.
public class BoardDAO {
	
	@Autowired	// 빈으로 등록해놨기 때문에 의존성 주입 해줘야겠죠?
	private JdbcTemplate jdbcTemplate;
	
	
	/**
	 * 게시글 목록 조회
	 * @return
	 */
	public List<Board> list() {
		
		String sql = " SELECT * FROM board ORDER BY board_no DESC";
		
		List<Board> boardList = jdbcTemplate.query(sql, new RowMapper<Board>() { // RowMapper는 인터페이스로 객체를 생성할 수 없어서 에러뜨면 unimplemented method 눌러 아래 Override 내용 추가하면 된다.
			
			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getTimestamp("reg_date"));
				board.setUpdDate(rs.getTimestamp("upd_date"));
				board.setViews(rs.getInt("views"));
				
				return board;
			}
		});
		
		return boardList;
	}
	
	
	
	/**
	 * 게시글 조회
	 * @return
	 */
	public Board select(int boardNo) {
		
		String sql = " SELECT * FROM board WHERE board_no = ? ";
		
		// 방법1)
//		List<Board> boardList = jdbcTemplate.query(sql, new RowMapper<Board>() { // RowMapper는 인터페이스로 객체를 생성할 수 없어서 에러뜨면 unimplemented method 눌러 아래 Override 내용 추가하면 된다.
//			
//			@Override
//			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
//				
//				Board board = new Board();
//				
//				board.setBoardNo(rs.getInt("board_no"));
//				board.setTitle(rs.getString("title"));
//				board.setWriter(rs.getString("writer"));
//				board.setContent(rs.getString("content"));
//				board.setRegDate(rs.getTimestamp("reg_date"));
//				board.setUpdDate(rs.getTimestamp("upd_date"));
//				board.setViews(rs.getInt("views"));
//				
//				return board;
//			}
//		}, boardNo);	// 이 boardNo가 sql의 ?에 매핑이 된다.
//		
//		
//		Board board = null;
//		
//		if(boardList.isEmpty()) {
//			board = null;
//		} else {
//			board = boardList.get(0);
//		}
		
		
		// 방법2)
		Board board = jdbcTemplate.queryForObject(sql, new RowMapper<Board>() {
			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board board = new Board();
				board.setBoardNo( rs.getInt("board_no") );
				board.setTitle( rs.getString("title") );
				board.setWriter( rs.getString("writer") );
				board.setContent( rs.getString("content") );
				board.setRegDate( rs.getTimestamp("reg_date") );
				board.setUpdDate( rs.getTimestamp("upd_date") );
				board.setViews( rs.getInt("views") );
				return board;
			}
		}, boardNo);
		
		
		// 방법3)
//		Object[] argList = new Object[]{boardNo};
//		Board board = jdbcTemplate.queryForObject(sql, argList, new RowMapper<Board>() {
//			@Override
//			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Board board = new Board();
//				board.setBoardNo( rs.getInt("board_no") );
//				board.setTitle( rs.getString("title") );
//				board.setWriter( rs.getString("writer") );
//				board.setContent( rs.getString("content") );
//				board.setRegDate( rs.getTimestamp("reg_date") );
//				board.setUpdDate( rs.getTimestamp("upd_date") );
//				board.setViews( rs.getInt("views") );
//				return board;
//			}
//		});
		
		return board;
	}
	
	
	
	/**
	 * 게시글 등록
	 * @return
	 */
	public int insert(Board board) {
		
		String sql = " INSERT INTO board(title, writer, content) "
				   + " VALUES ( ?, ?, ? ) ";
		
		Object[] args = new Object[] {board.getTitle(), board.getWriter(), board.getContent()};
		
		int result = jdbcTemplate.update(sql, args);
		
		// 방법2)
		// int result = jdbcTemplate.update(sql, board.getTitle(), board.getWriter(), board.getContent());
		
		return result;
	}
	
	
	
	/**
	 * 게시글 수정
	 * @return
	 */
	public int update(Board board) {
		
		String sql = " UPDATE board "
				   + "    SET title = ? "
				   + "       ,writer = ? "
				   + "       ,content = ? "
				   + "		 ,upd_date = now() "
				   + " WHERE board_no = ? ";
		
		Object[] args = new Object[] {board.getTitle(), board.getWriter(), board.getContent(), board.getBoardNo()};
		
		int result = jdbcTemplate.update(sql, args);
		
		return result;
	}
	
	
	
	/**
	 * 게시글 삭제
	 * @param boardNo
	 * @return
	 */
	public int delete(int boardNo) {
		String sql = " DELETE FROM board "
				   + " WHERE board_no = ? ";
		
		Object[] args = new Object[] {boardNo};
		
		int result = jdbcTemplate.update(sql, args);
		
		return result;
	}
}
