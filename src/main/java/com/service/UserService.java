package com.service;

import com.model.Money;
import com.model.User;

import java.util.HashMap;
import java.util.Map;

public interface UserService {
    //返回登陆人的信息
    User login(User user) throws Exception;
    //返回个人信息，其实只是返回余额，因为个人的信息都保存在session中了
    Money personalInfo(String userName) throws Exception;
    //用户注册
    void register(User userRegister)throws Exception;
    //用户注册后把用户名和余额写入money表
    void userNameAndBalance(Map<String,Object> map) throws Exception;
    //充值
    void userRecharge(Map<String,Object> map)throws Exception;
    //修改手机号和邮箱
    void modifyPhoneAndEmail(Map<String,Object> map) throws Exception;
    //取消修改手机号和密码
    HashMap<String,Object> cancelModifyPhoneAndEmail(String userName) throws Exception;
    //修改密码
    void modifyPassword(Map<String,Object> map)throws Exception;
    //用手机号找回的用户名
    String findUserNameByPhone(String phone)throws Exception;
    //利用手机号找回的密码
    String findUserPasswordByPhone(String phone) throws Exception;
    //通过邮件找回的手机号码
    String findPhoneByEmail(String email)throws Exception;

}
