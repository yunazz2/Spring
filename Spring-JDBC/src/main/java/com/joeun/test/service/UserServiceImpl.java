package com.joeun.test.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joeun.test.dao.UserDAO;
import com.joeun.test.dto.User;
import com.joeun.test.dto.UserAuth;

@Service // 비즈니스 로직을 처리하는 서비스 클래스로 빈을 등록하겠다.	
public class UserServiceImpl implements UserService {
	
	// 로그를 찍어보는 내용(HomeController에서 가지고 옴)
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDAO userDAO;
	
	// 구현하려면 UserService에 추상 메소드로 만들어두었던 걸 오버라이딩 해야겠죠?
	@Override
	public List<User> list() throws Exception {
		
		List<User> userList = userDAO.list();
		
		return userList;
	}

	@Override
	public User select(int userNo)  {
		
		User user = userDAO.select(userNo);
		
		return user;
	}

	// 회원 가입(등록)
	@Transactional	// 트랜잭션(A가 B한테 10000원 송금을 했을 때, A는 -10000원, B는 +10000원 이게 동시에 일어나야겠죠?)
	@Override
	public Integer insert(User user) throws Exception {
		
		int result = userDAO.insert(user);
		
		UserAuth userAuth = new UserAuth();
		userAuth.setAuth("ROLE_USER");
		userAuth.setUserId(user.getUserId());
		userDAO.insertAuth(userAuth);
		
		return result;
	}
	
	

	@Override
	public Integer update(User user) throws Exception {
		
		int result = userDAO.update(user);
		
		return result;
	}

	@Override
	public Integer delete(int userNo) throws Exception {
		
		int result = userDAO.delete(userNo);
		
		return result;
	}

	@Override
	public User login(User user) throws Exception {
		
		User loginUser = userDAO.login(user);
		
		return loginUser;
	}

}