package com.service.impl;

import com.dao.UserDao;
import com.model.Money;
import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    //返回用户信息fwf
    @Override
    public User login(User user) throws Exception {
        User user1 = userDao.login(user);
        //用户名错误或者用户名正确而密码错误
        if(user1 == null||user.getPasswordd() != user1.getPasswordd()){
           return null;
        }else{
            //用户名和密码都正确
            return user1;
        }
    }
    //返回余额
    @Override
    public Money personalInfo(String userName) throws Exception {
        return userDao.personalInfo(userName);
    }

    //用户注册
    @Override
    public void register(User userRegister) throws Exception {
        userDao.register(userRegister);
    }

    //用户注册后把用户名和余额写入money表
    @Override
    public void userNameAndBalance(Map<String, Object> map) throws Exception {
        userDao.userNameAndBalance(map);
    }

    //充值
    @Override
    public void userRecharge(Map<String,Object> map) throws Exception {
        userDao.userRecharge(map);
    }

    //修改手机号和邮箱
    @Override
    public void modifyPhoneAndEmail(Map<String, Object> map) throws Exception {
        userDao.modifyPhoneAndEmail(map);
    }

    //取消手机号和邮箱的修改
    @Override
    public HashMap<String,Object> cancelModifyPhoneAndEmail(String userName) throws Exception {
        return userDao.cancelModifyPhoneAndEmail(userName);
    }

    //修改密码
    @Override
    public void modifyPassword(Map<String, Object> map) throws Exception {
        userDao.modifyPassword(map);
    }

    //利用手机号找回的用户名
    @Override
    public String findUserNameByPhone(String phone) throws Exception {
        return userDao.findUserNameByPhone(phone);
    }

    //利用手机号找回的密码
    @Override
    public String findUserPasswordByPhone(String phone) throws Exception {
        return userDao.findUserPasswordByPhone(phone);
    }

    //利用邮件找回的手机号
    @Override
    public String findPhoneByEmail(String email) throws Exception {
        return userDao.findPhoneByEmail(email);
    }


}
