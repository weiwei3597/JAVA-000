package com.bean;
/**
 *
 **/

/**
 * @Author weiwei
 * @Date 2020-11-13 11:35
 * @description
 **/
public class StudentFactory {



    public Student getStudent(){
        Student student=new Student();
        student.setId("1");
        student.setName("张三");
        return student;
    }
}
