package com.ww.rpc.service.impl;
/**
 *
 **/

import com.ww.rpc.api.UserService;
import com.ww.rpc.domain.User;

/**
 * @Author weiwei
 * @Date 2020-12-14 10:15
 * @description
 **/
public class UserServiceImpl implements UserService {



    @Override
    public User findUserById(Integer id) {
        return new User(id,"ww");
    }
}
