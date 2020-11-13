package com.bean;
/**
 *
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author weiwei
 * @Date 2020-11-13 09:57
 * @description 学生类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {

    private String id;

    private String name;

}
