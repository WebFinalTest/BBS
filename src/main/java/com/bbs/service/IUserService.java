package com.bbs.service;

import com.bbs.entity.User;

import java.util.List;

public interface IUserService {
    //返回所有用户
    public List<User> findAll();

    //按照id查询user
    public User findByUserId(Long userId);

    //注册用户
    public User register(User user);

    //更新用户信息
    public void update(User user);

    //登陆用户
    public User login(String email,String password);

    //查找邮箱是否被使用
    public boolean isUsedByEmail(String email);

    //查找用户昵称是否被使用
    public boolean isUsedByUserName(String userName);

    //修改密码
    public boolean updatePasswordByUserId(Long userId,String originalPassword,String newPassword);

    //不允许删除用户
//    public void deleteById(Long id);
}
