package com.ww.rpc.api;
/**
 *
 **/

import com.ww.rpc.domain.User;

/**
 * @author: weiwei
 * @create: 2020-12-14 10:14
 * @description:
 **/
public interface UserService {


    User findUserById(Integer id);
}
