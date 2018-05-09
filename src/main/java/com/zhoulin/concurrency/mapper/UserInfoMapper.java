package com.zhoulin.concurrency.mapper;


import com.zhoulin.concurrency.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    UserInfo getUserByUsername(String username);

    List<UserInfo> getAllUserInfo() throws Exception;

    UserInfo getUserInfoById(Integer userId) throws Exception;

    Integer addUserInfo(UserInfo userInfo) throws Exception;

    Integer updateUserInfo(UserInfo userInfo) throws Exception;

    Integer deleteUserInfoById(Integer userId) throws Exception;

    boolean existEmail(String userMail) throws Exception;

    UserInfo findUserPW(String username, String password) throws Exception;
}
