package com.joeun.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joeun.test.dto.Person;

@Controller	// 이 클래스를 컨트롤러로 사용할 때 기본적으로 컨트롤러 어노케이션을 붙인다.
@RequestMapping("/model")
public class ModelController {
	
	// logger
	private static final Logger logger = LoggerFactory.getLogger(ModelController.class);
	
	
	@RequestMapping("/person")	// method : GET(기본값) -> 여기에 경로만 설정해놓으면 기본 메소드 요청 방식은 GET 방식이다.
	public String person(Model model, Person person) {
		// Model 인터페이스의 addAttribute() 메소드로 데이터를 등록하여, 뷰에 전달한다.
		// Model
		// : 컨트롤러에서 등록한 데이터를 뷰에 전달하는 인터페이스
		
		// Person person = new Person();
		// person.setName("이유나");
		// person.setAge(28);
		
		
		// addAttribute("변수명", 객체) 메소드로 모델에 객체를 등록한다.
		// model.addAttribute("person", person);	// view에서 ${} (표현언어)로 사용할 수 있다.
		model.addAttribute(person);					// 변수명을 생략하면 객체명과 일치하는 이름으로 접근할 수 있다.
													// ${person.name}, ${person.age}
													// getter 메소드가 정의되어있어야 위와 같이 뷰에서 접근이 가능하다.
													// person 클래스에 보면 name, age 모두 private로 지정되어있다. private은 외부에서 접근이 불가능한데,
													// getter, setter가 있기 때문에 ln28처럼 접근이 가능하다.


		return "model/person";						// view : model/person.jsp
	}
	
	
	
	/**
	 * 요청 경로 : /model/person2?name=이유나&age=28
	 * 			* 요청 파라미터의 변수명들을 객체의 필드(멤버변수)로 자동 매핑 해준다.
	 * @param person
	 * @return
	 */
	@RequestMapping("/person2")
	public String person2(@ModelAttribute Person person) {
		
		// @ModelAttribute : 매개변수를 모델에 자동으로 등록해주는 어노테이션
		// 원래는 model.addAttribute("person", person); 이렇게 써줘야하는데 ModelAttribute로 인해 매개 변수를 자동으로 등록해준다.
		
		
		return "model/person";						// view : model/person.jsp
		
		// http://localhost:8080/Spring-MVC/model/person2?name=이유나&age=28
		// 이렇게 주소창에서 파라미터에 직접 name과 age를 넘기면 원래 [이름 : , 나이 : 0]으로 나오던 게 자동 매핑되어 [이름 : 이유나, 나이 : 28]로 나오게 된다. 
		
	}
	
	
	
	/**
	 * 요청 경로 : /model/person
	 * method : POST
	 * POST 방식이기 때문에 주소창에 파라미터로(?name=이유나&age=28) 나오지 않고 <body>에 나온다.
	 * @param person
	 * @return
	 */
	@RequestMapping(value = "/person", method = RequestMethod.POST)
	// public String personPost(@ModelAttribute Person person) {

	// public String personPost(@RequestParam("name") String name, @RequestParam("age") int age) { 	// ln73과 같은 내용이다.
	// public String personPost(Model model, String name, int age) { 									// ln73과 같은 내용이다.
	// 근데 지금 바로 위 처럼 쓰면, Person 객체를 불러와서 모델에 등록해줘야한다. 그러니깐! 편하기는 당연히 ln73이 편하겠죠? 자동 매핑 해주니까~
	// - @RequestParam("요청파라미터명") 데이터타입 메소드매개변수명
	// * 요청파라미터명 = 메소드매개변수명 일치하면 생략 가능
	
	public String personPost(Model model, Person person, RedirectAttributes rttr) {
		// RedirectAttributes 인터페이스
		// : 리다이렉트 방식으로 페이지 이동 시, 일회성으로 데이터를 전달하는 인터페이스
		
//		Person person = new Person();
//		
//		person.setName(name);
//		person.setAge(age);
		
		// forward 방식으로 페이지 이동 시, 모델에 데이터 사용 가능
		model.addAttribute(person);
		
		// redirect 방식으로 페이지 이동 시, RedirectAttributes 인터페이스를 통해서 일회성으로 데이터 전달 가능
		// 리다이렉트로 요청 시 딱 그 때만 일회성으로 정보를 전달한다! 그렇기 때문에 새로고침을 하면 데이터가 사라진다!
		rttr.addFlashAttribute("person", person);


		// 리다이렉트: redirect:/요청경로
		// - 최초 요청 객체는 사라진다.
		return "redirect:/model/person";				// view : model/person.jsp
	}
}
