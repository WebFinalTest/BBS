package com.bbs;

import com.bbs.entity.User;
import com.bbs.service.IUserService;
import com.bbs.util.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Test
    public void test(){
        //登陆方法
        System.out.println("登陆方法：");
        User user = userService.login("1367112248@qq.com","2");
        System.out.println(user);


        //注册方法
        System.out.println("注册方法：");
        user.setEmail(Utils.randomId(10).toString());
        user.setUserName(Utils.randomId(10).toString());
        user = userService.register(user);
        System.out.println(user);

        //查找所有用户
        System.out.println("找所有用户：");
        List<User> users = userService.findAll();
        System.out.println(users);

        //更新
        System.out.println("更新：");
        user = userService.login("1367112248@qq.com","2");
        user.setHabitation("湖南省");
        userService.update(user);
        user = userService.login("1367112248@qq.com","2");
        System.out.println(user);

        //查看邮箱是否被使用
        System.out.println("查看邮箱是否被使用：");
        System.out.println(userService.isUsedByEmail("1367112248@qq.com"));

        //查看用户名是否被使用
        System.out.println("查看用户名是否被使用：");
        System.out.println(userService.isUsedByEmail("2"));

    }
}
