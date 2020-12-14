package com.ww.rpc.domain;
/**
 *
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author weiwei
 * @Date 2020-12-14 10:14
 * @description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    private String name;
}
