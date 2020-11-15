package com.work.springboot;

import com.alibaba.fastjson.JSON;
import com.bean.ISchool;
import com.bean.Klass;
import com.bean.School;
import com.bean.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApplicationTests {

	@Autowired
	@Qualifier("student100")
	private Student student;

	@Autowired
	private Klass klass;

	@Autowired
	private ISchool school;



	@Test
	void contextLoads() {
		System.out.println(JSON.toJSON(student));
		System.out.println(JSON.toJSON(klass));
		System.out.println(JSON.toJSON(school));
	}

}
