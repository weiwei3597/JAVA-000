package com.ww.rpc.core.domain;
/**
 *
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author weiwei
 * @Date 2020-12-14 10:08
 * @description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest {


    private String serviceClass;

    private String method;

    private Object[] param;
}
