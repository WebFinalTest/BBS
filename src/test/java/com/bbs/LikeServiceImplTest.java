package com.bbs;

import com.bbs.Application;
import com.bbs.entity.Like;
import com.bbs.repository.ICommentRepository;
import com.bbs.repository.ILikeRepository;
import com.bbs.service.ILikeService;
import com.bbs.service.impl.LikeServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class LikeServiceImplTest{
    ILikeService likeService;
    ICommentRepository iCommentRepository;

    @Autowired
    public void setiCommentRepository(ICommentRepository iCommentRepository) {
        this.iCommentRepository = iCommentRepository;
    }

    @Autowired
    public void setLikeService(ILikeService likeService) {
        this.likeService = likeService;
    }

    @Test
    public void test(){
        Like l1=new Like();
        Like l2=new Like();
        Like l3=new Like();
        Like l4=new Like();
        l1.setUserId(1L);
        l2.setUserId(2L);
        l3.setUserId(3L);
        l4.setUserId(4L);
        l1.setPostId(5L);
        l2.setPostId(5L);
        l3.setCommentId(6L);
        l4.setCommentId(6L);
//        likeService.createLike(l1);
//        likeService.createLike(l2);
//        likeService.createLike(l3);
//        likeService.createLike(l4);
//        List<Like> likes1=likeService.findPostLikes(l1.getUserId());
//        for(Like l:likes1){
//            System.out.println(l);
//        }
//        System.out.println(likeService.countPostLikes(l1.getPostId()));
//        if(likeService.isPostLike(l1.getPostId())){
//            System.out.println("l1点赞成功");
//        }
//        else{
//            System.out.println("l1点赞失败");
//        }
//        List<Like> likes2=likeService.findCommentLikes(l3.getUserId());
//        for(Like l:likes2){
//            System.out.println(l);
//        }
//        System.out.println(likeService.countCommentLike(l3.getCommentId()));
//        if(likeService.isCommentLike(l3.getCommentId())){
//            System.out.println("l3点赞成功");
//        }
//        else{
//            System.out.println("l3点赞失败");
//        }
        likeService.deleteLikeByCommentId(l3.getCommentId(),l3.getUserId());
        likeService.deleteLikeByPostId(l2.getPostId(),l2.getUserId());
    }
}

