package com.joeun.test.dto;

import lombok.Data;

@Data // pom.xml에 lombok 내용이 있으면 이렇게 쓰기만해도 getter, setter가 자동으로 생성된다.
public class Person {

	private String name;
	private int age;
}
