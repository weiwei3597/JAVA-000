package com.bean;
/**
 *
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author weiwei
 * @Date 2020-11-13 11:09
 * @description 注解装配
 **/
@Configuration
public class AnnotationAssemble {

    @Resource(name = "beanAssembleAnnotationSetTest1")
    private Student student1;

    @Resource(name = "beanAssembleAnnotationSetTest2")
    private Student student2;

    @Bean("beanAssembleAnnotationSetTest1")
    public Student getStudent(){
        Student student = new Student();
        student.setId("1");
        student.setName("张三1");
        return student;
    }

    @Bean("beanAssembleAnnotationSetTest2")
    public Student getStudent2(){
        Student student = new Student();
        student.setId("2");
        student.setName("李四1");
        return student;
    }


    @Bean("beanAssembleAnnotationSetTest3")
    public Class getClassBean(){
       Class classBean = new Class();
        List<Student> studentList = new LinkedList<>();
        studentList.add(student1);
        studentList.add(student2);
        classBean.setStudentList(studentList);
        return classBean;
    }
}
