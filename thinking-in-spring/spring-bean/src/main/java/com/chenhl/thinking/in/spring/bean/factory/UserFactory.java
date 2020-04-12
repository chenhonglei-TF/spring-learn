package com.chenhl.thinking.in.spring.bean.factory;

import com.chenhl.thinking.in.spring.ioc.overview.domain.User;

public interface UserFactory {

    default User getUser(){
        return User.createUser();
    }



}
