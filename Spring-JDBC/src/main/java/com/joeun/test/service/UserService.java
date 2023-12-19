/**
 *  핵심 비즈니스 로직은 여기서부터 출발
 */
package com.joeun.test.service;

import java.util.List;

import com.joeun.test.dto.User;

public interface UserService {
	
	// 주요 기능들만 추상 메소드로 아래와 같이 작성한다.
	// 구현은 UserServiceImple 클래스에서.
	
	// 회원 목록
	public List<User> list() throws Exception;
	
	// 회원 조회
	public User select(int userNo) throws Exception;
	
	// 로그인
	public User login(User user) throws Exception;
	
	// 회원 등록
	public Integer insert(User user) throws Exception;

	// 회원 수정
	public Integer update(User user) throws Exception;
	
	// 회원 삭제
	public Integer delete(int userNo) throws Exception;
}
