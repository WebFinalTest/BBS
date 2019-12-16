package com.bbs;

import com.bbs.repository.IUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    private IUserRepository userRepository;

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void test(){
        //增加积分
        userRepository.increasePointsByUserId(100L,123226946L);
        System.out.println("增加积分");
        System.out.println(userRepository.findByUserId(123226946L));

        //减少积分
        userRepository.reducePointsByUserId(100L,123226946L);
        System.out.println("增加积分");
        System.out.println(userRepository.findByUserId(123226946L));
    }
}
