package com.bbs.repository;

import com.bbs.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository {
    public List<User> findAll();

    public void save(User user);
    public void update(User user);
//    public void deleteByUserId(Long userId);

    //按照密码和邮箱查找用户
    public User login(@Param("email")String email, @Param("password")String password);

    //按照邮箱查找用户
    public User findByEmail(String email);

    //按照用户名查找用户
    public User findByUserName(String userName);

    //按照用户ID查找用户
    public User findByUserId(Long userId);

    //增加积分
    public void increasePointsByUserId(@Param("points") Long points,@Param("userId") Long userId);

    //减少积分
    public void reducePointsByUserId(@Param("points") Long points,@Param("userId") Long userId);

}