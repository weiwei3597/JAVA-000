package io.kimmking.dubbo.demo.api;
/**
 *
 **/

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author weiwei
 * @Date 2020-12-18 15:08
 * @description
 **/
@Data
public class Freeze {

    private Long id;

    private Long accountId;

    private String type;

    private BigDecimal money;

    private Date createTime;


}
