package com.bbs;

import com.bbs.entity.Comment;
import com.bbs.service.ICommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {
    private ICommentService commentService;

    @Autowired
    public void setCommentService(ICommentService commentService) {
        this.commentService = commentService;
    }

    @Test
    public void test(){
        //创建一个评论
        Comment comment = new Comment();
        comment.setCommentContent("啊哈哈哈！");
        comment.setPostId(1L);
        comment.setReplyId(2L);
        comment.setFloorId(2L);
        comment.setUserId(123226946L);
        commentService.createComment(comment);

        //返回一个帖子的所有评论
        System.out.println("返回一个帖子的所有评论");
        List<Comment> comments = commentService.findByPostId(1L);
        System.out.println(comments);

        //返回一个层级所有的评论
        System.out.println("返回一个层级的所有评论");
        comments = commentService.findByFloorId(2L);
        System.out.println(comments);

        //返回一个用户的所有评论
        System.out.println("返回一个用户的所有评论");
        comments = commentService.findByPostId(1L);
        System.out.println(comments);

        //删除一个评论
        commentService.deleteByCommentId(183025142983L);

    }
}
