package com.bbs.controller;

import com.bbs.entity.Comment;
import com.bbs.entity.Post;
import com.bbs.entity.User;
import com.bbs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/Comment")
@Controller
public class CommentHandler {
    private ICommentService commentService;
    private IPostService postService;
    private IUserService userService;
    private ILikeService likeService;
    private ICollectService collectService;

    @Autowired
    public void setCollectService(ICollectService collectService) {
        this.collectService = collectService;
    }

    @Autowired
    public void setLikeService(ILikeService likeService) {
        this.likeService = likeService;
    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCommentService(ICommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setPostService(IPostService postService) {
        this.postService = postService;
    }

    //跳转到某个帖子的里面
    @GetMapping("/goPostByPostId/{postId}")
    public String goComments(@PathVariable("postId") Long postId, Model model,HttpSession session){
        List<Comment> comments = new ArrayList<>();
        Post post = new Post();
        String postUserName = "";
        Map<Long,Long> countFloorComments = new HashMap<>();
        Map<Long,String> commentUserNames = new HashMap<>();
        Comment adoptComment = new Comment();
        Map<Long,Boolean> isLikeComment = new HashMap<>();
        Boolean isLikePost = false,isCollectPost = false;
        try {
            User user = (User) session.getAttribute("user");
            comments = commentService.findByPostId(postId,1L);
            post = postService.findPostByPostId(postId);
            postUserName = userService.findByUserId(post.getUserId()).getUserName();
            //加入采纳评论
            if(post.isPostType() && post.getAdoptCommentId() != null){
                adoptComment = commentService.findByCommentId(post.getAdoptCommentId());
                commentUserNames.put(adoptComment.getUserId(),userService.findByUserId(adoptComment.getUserId()).getUserName());
            }
            for (Comment comment: comments) {
                countFloorComments.put(comment.getCommentId(),commentService.countCommentsByFloorId(comment.getCommentId()));
                commentUserNames.put(comment.getUserId(),userService.findByUserId(comment.getUserId()).getUserName());
                isLikeComment.put(comment.getCommentId(),likeService.isLikeCommentByUserId(comment.getCommentId(),user.getUserId()));
            }
            isCollectPost = collectService.isCollectPostByUserId(postId,user.getUserId());
            isLikePost = likeService.isLikePostByUserId(postId,user.getUserId());
        }catch (Exception e) {
            System.out.println("ERROR:goComments");
        }
        model.addAttribute("isLikeComment",isLikeComment);
        model.addAttribute("isLikePost",isLikePost);
        model.addAttribute("isCollectPost",isCollectPost);
        model.addAttribute("adoptComment",adoptComment);
        model.addAttribute("comments",comments);
        model.addAttribute("post",post);
        model.addAttribute("countFloorComments",countFloorComments);
        model.addAttribute("postUserName",postUserName);
        model.addAttribute("commentUserNames",commentUserNames);
        return "post/show";
    }

    //查看更多贴子的主评论
    @PostMapping("/moreComments")
    @ResponseBody
    public List goMoreComments(Long postId,Long page,HttpSession session) {
        List<Comment> comments;
        List<CommentInfo> commentInfos = new ArrayList<>();
        try{
            User user = (User)session.getAttribute("user");
            comments = commentService.findByPostId(postId,page);
            for (Comment comment:comments) {
                CommentInfo commentInfo = new CommentInfo();
                commentInfo.comment = comment;
                commentInfo.userName = userService.findByUserId(comment.getUserId()).getUserName();
                commentInfo.isLike = likeService.isLikeCommentByUserId(comment.getCommentId(),user.getUserId());
//                commentInfo.replyContent = commentService.findByCommentId(comment.getReplyId()).getCommentContent();
                commentInfos.add(commentInfo);
            }
        }catch (Exception e){
            System.out.println("ERROR:goMoreComments");
        }
        return commentInfos;
    }

    //删除评论
    @GetMapping("/deleteComment")
    @ResponseBody
    public Map deleteComment(Long commentId) {
        Map<String,String> result = new HashMap<>();
        try {
            if(commentService.deleteByCommentId(commentId))
                result.put("message","success");
            else
                result.put("message","fail");
        }catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }

    //查看层级评论
    @GetMapping("/goFloorComment/{floorId}")
    public String goFloorComment(@PathVariable("floorId") Long floorId,Model model,HttpSession session) {
        List<Comment> comments = new ArrayList<>();
        Map<Long,String> replyComments = new HashMap<>();
        Map<Long,String> commentUserNames = new HashMap<>();
        Map<Long,Boolean> isLikeComment = new HashMap<>();
        Comment floorComment = new Comment();
        try {
            User user = (User)session.getAttribute("user");
            floorComment = commentService.findByCommentId(floorId);
            Comment replyComment;
            comments = commentService.findByFloorId(floorId,1L);
            for (Comment comment : comments) {
                if(!comment.getReplyId().equals(floorComment.getCommentId())
                        && replyComments.get(comment.getReplyId()) == null) {
                    replyComment = commentService.findByCommentId(comment.getReplyId());
                    replyComments.put(comment.getReplyId(),replyComment.getCommentContent());
                }
                if(commentUserNames.get(comment.getUserId()) == null) {
                    commentUserNames.put(comment.getUserId(),userService.findByUserId(comment.getUserId()).getUserName());
                }
                isLikeComment.put(comment.getCommentId(),likeService.isLikeCommentByUserId(comment.getCommentId(),user.getUserId()));
            }
            commentUserNames.put(floorId,userService.findByUserId(floorComment.getUserId()).getUserName());
        }
        catch (Exception e) {
            System.out.println("Error:goFloorComments");
        }
        model.addAttribute("isLikeComment",isLikeComment);
        model.addAttribute("replyComments",replyComments);
        model.addAttribute("floorComment",floorComment);
        model.addAttribute("commentUserNames",commentUserNames);
        model.addAttribute("comments",comments);
        return "comment/showComment";
    }

    //查看更多帖子的某层评论
    @PostMapping("/moreFloorComments")
    @ResponseBody
    public List goMoreFloorComments(Long floorId,Long page,HttpSession session) {
        List<Comment> comments;
        List<CommentInfo> commentInfos = new ArrayList<>();
        try{
            User user = (User)session.getAttribute("user");
            comments = commentService.findByFloorId(floorId,page);

            for (Comment comment:comments) {
                CommentInfo commentInfo = new CommentInfo();
                commentInfo.comment = comment;
                commentInfo.userName = userService.findByUserId(comment.getUserId()).getUserName();
                commentInfo.isLike = likeService.isLikeCommentByUserId(comment.getCommentId(),user.getUserId());
                if (!comment.getReplyId().equals(floorId))
                    commentInfo.replyContent = commentService.findByCommentId(comment.getReplyId()).getCommentContent();
                commentInfos.add(commentInfo);
            }
        }catch (Exception e){
            System.out.println("ERROR:goMoreComments");
        }
        return commentInfos;
    }

    //创建评论
    @PostMapping("/create")
    @ResponseBody
    public Map createComment (HttpSession session,Comment comment) {
        Map<String,String> result = new HashMap<>();
        try {
            User user = (User) session.getAttribute("user");
            comment.setUserId(user.getUserId());
            commentService.createComment(comment);
            user = userService.findByUserId(user.getUserId());
            user.setPassword("");
            session.setAttribute("user",user);
            result.put("message","success");
        }catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }

    //返回数据的包装类
    class CommentInfo{
        Comment comment;
        String userName;
        boolean isLike;
        String replyContent;

        public Comment getComment() {
            return comment;
        }

        public String getUserName() {
            return userName;
        }

        public boolean isLike() {
            return isLike;
        }

        public String getReplyContent() {
            return replyContent;
        }

//        //根据评论包装成评论信息
//        public void transfer(Comment comment,User user){
//            CommentInfo commentInfo = new CommentInfo();
//            commentInfo.comment = comment;
//            commentInfo.userName = userService.findByUserId(comment.getUserId()).getUserName();
//            commentInfo.isLike = likeService.isLikeCommentByUserId(comment.getCommentId(),user.getUserId());
//            commentInfo.replyContent = commentService.findByCommentId(comment.getReplyId()).getCommentContent();
//        }
    }
}
