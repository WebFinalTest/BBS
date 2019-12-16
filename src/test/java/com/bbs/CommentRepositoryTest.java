package com.bbs;

import com.bbs.entity.Comment;
import com.bbs.repository.ICommentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTest {
    private ICommentRepository commentRepository;

    @Autowired
    public void setCommentRepository(ICommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Test
    public void test() {
        //点赞评论
        commentRepository.likeByCommentId(2L);

        //按ID查评论
        Comment comment = commentRepository.findByCommentId(2L);
        System.out.println(comment);

        //取消点赞评论
        commentRepository.unlikeByCommentId(2L);

        comment = commentRepository.findByCommentId(2L);
        System.out.println(comment);
    }
}
