package com.mysql.demo.test;
/**
 *
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author weiwei
 * @Date 2020-12-02 22:23
 * @description
 **/
@SpringBootTest
public class Test {

//   @Autowired
//   @Qualifier("mJdbcTemplate")
//   private JdbcTemplate mJdbcTemplate;
//
//    @Autowired
//    @Qualifier("sJdbcTemplate")
//    private JdbcTemplate sJdbcTemplate;

    @Autowired
    @Qualifier("ssJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

//
//    @org.junit.jupiter.api.Test
//    public void test(){
//        mJdbcTemplate.update("INSERT INTO USER (id,username,phone,code) VALUES (?,?,?,?)", 2,"123","78","10");
//        sJdbcTemplate.update("INSERT INTO USER (id,username,phone,code) VALUES (?,?,?,?)", 2,"123","78","10");
//    }

    @org.junit.jupiter.api.Test
    public void test2(){
        jdbcTemplate.update("INSERT INTO USER (id,username,phone,code) VALUES (?,?,?,?)", 3,"123","78","10");
        String s = jdbcTemplate.queryForObject("SELECT phone FORM USER WHERE ID = 2", String.class);
        System.out.println(s);
    }


}
