package io.kimmking.dubbo.demo.api;
/**
 *
 **/

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author weiwei
 * @Date 2020-12-18 15:01
 * @description
 **/
@Data
public class Account {

    private Long id;

    private String name;

    private BigDecimal RMB;

    private BigDecimal dollar;
}
