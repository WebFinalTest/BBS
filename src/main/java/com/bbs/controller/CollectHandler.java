package com.bbs.controller;

import com.bbs.entity.Collect;
import com.bbs.entity.Favorites;
import com.bbs.entity.Post;
import com.bbs.entity.User;
import com.bbs.service.ICollectService;
import com.bbs.service.IFavoritesService;
import com.bbs.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/Collect")
@Controller
public class CollectHandler {
    private ICollectService collectService;
    private IFavoritesService favoritesService;
    private IPostService postService;

    @Autowired
    public void setPostService(IPostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setCollectService(ICollectService collectService) {
        this.collectService = collectService;
    }

    @Autowired
    public void setFavoritesService(IFavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }


    //创建一个新的收藏
    @PostMapping("/createCollect")
    @ResponseBody
    public Map createCollect(HttpSession session ,Long postId){
        Map<String,String> result = new HashMap<String, String>();
        User user = (User) session.getAttribute("user");
        Collect collect = new Collect();
        collect.setUserId(user.getUserId());
        collect.setPostId(postId);
        try{
            List<Favorites> favoritesList = favoritesService.findAllFavoritesByUserId(user.getUserId());
            collect.setFavoritesId(favoritesList.get(0).getFavoritesId());
            collectService.createCollect(collect);
            result.put("message","success");
        }
        catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }

    //获取新的收藏夹Id，把此收藏放在另一个收藏夹
    @PostMapping("/replaceCollect")
    @ResponseBody
    public Map replaceCollect(Collect collect){
        Map<String,String> result = new HashMap<String, String>();
        try{
            collectService.updateCollect(collect.getPostId(),collect.getUserId(),collect.getFavoritesId());
            result.put("message","success");
        }
        catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }


    //查看收藏夹的全部内容
    @GetMapping("/viewCollect/{favoritesId}")
    public String viewCollect(@PathVariable("favoritesId") Long favoritesId, Model model){
        List<Post> list;
        try{
            list = postService.findCollectPostsByUserId(favoritesId);
        }
        catch (Exception e){
            list = null;
        }
        model.addAttribute("collects",list);
        return "collect/showPosts";
    }

    //删除一个收藏
    @PostMapping("/deleteCollect")
    @ResponseBody
    public Map deleteCollect(HttpSession session,Long postId){
        Map<String,String> result = new HashMap<String, String>();
        User user = (User) session.getAttribute("user");
        try{
            collectService.deleteCollect(postId,user.getUserId());
            result.put("message","success");
        }
        catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }
}
