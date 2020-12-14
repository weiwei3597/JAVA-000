package com.ww.rpc.core.domain;
/**
 *
 **/

import lombok.Data;

/**
 * @Author weiwei
 * @Date 2020-12-14 10:09
 * @description
 **/
@Data
public class RpcResponse {

    private Object result;

    private Boolean status;

    private Exception exception;
}
