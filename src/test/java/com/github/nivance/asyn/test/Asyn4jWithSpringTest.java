package com.github.nivance.asyn.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Asyn4jWithSpringTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:*application.xml");
		SpringProcessor processor = context.getBean(SpringProcessor.class);
		processor.process("hello", 10);
	}

}
