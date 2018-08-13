package com.dreamcc.mall.mapper;

import com.dreamcc.mall.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int checkUsername(String username);

    int checkEmail(String email);

    User selectLogin(@Param("username") String username, @Param("password")String password);
}