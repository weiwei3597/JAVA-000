package com.bean;
/**
 *
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @Author weiwei
 * @Date 2020-11-13 10:30
 * @description 班级
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Class {

    private List<Student> studentList;
}
