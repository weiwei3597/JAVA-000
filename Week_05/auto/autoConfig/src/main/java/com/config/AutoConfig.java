package com.config;
/**
 *
 **/

import com.bean.ISchool;
import com.bean.Klass;
import com.bean.School;
import com.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @Author weiwei
 * @Date 2020-11-15 17:02
 * @description 自动装配
 **/

@Configuration
@ConditionalOnClass(value={Student.class,Klass.class,School.class})
@ConditionalOnProperty(name="customer.auto.config",havingValue = "true")
public class AutoConfig {

    @Autowired
    @Qualifier("student100")
    private Student student;


    @Bean("student100")
    public Student getStudent(){
        Student student=new Student();
        student.setId(1);
        student.setName("张三");
        return student;
    }

    @Bean()
    @ConditionalOnBean(name = "student100")
    public Klass getKlass(){
        Klass klass = new Klass();
        klass.setStudents(Collections.singletonList(student));
        return klass;
    }

    @Bean
    @ConditionalOnBean(name="student100",value={Student.class,Klass.class})
    public School getSchool(){
        return new School();
    }
}
