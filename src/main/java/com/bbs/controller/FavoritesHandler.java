package com.bbs.controller;

import com.bbs.entity.Favorites;
import com.bbs.entity.User;
import com.bbs.service.IFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/Favorites")
@Controller
public class FavoritesHandler {

    private IFavoritesService favoritesService;

    @Autowired
    public void setFavoritesService(IFavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    //创建一个新的收藏夹
    @PostMapping("/createFavorites")
    @ResponseBody
    public Map createFavorites(HttpSession session,String name){
        User user = (User) session.getAttribute("user");
        Map<String,String> result = new HashMap<String, String>();
        try{
            favoritesService.createFavorites(user.getUserId(),name);
            result.put("message","success");
        }
        catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }

    //根据userId查看收藏夹,返回该Id的全部收藏夹,错则返回null
    @PostMapping("/viewFavorites")
    @ResponseBody
    public List<Favorites> viewFavorites(HttpSession session){
        User user = (User)session.getAttribute("user");
        List<Favorites> list;
        try{
             list = favoritesService.findAllFavoritesByUserId(user.getUserId());
        }
        catch (Exception e) {
             list = null;
        }
        return  list;
    }

    //传入favorites对象,改变此favorites对象的名字
    @PostMapping("/renameFavorites")
    @ResponseBody
    public Map renameFavorites(Favorites favorites){
        Map<String,String> result = new HashMap<String, String>();
        try{
            favoritesService.updateFavorites(favorites.getFavoritesId(),favorites.getFavoritesName());
            result.put("message","success");
        }
        catch (Exception e) {
            result.put("message","error");
        }
            return result;
    }

    //传入favorites对象删除此favorites对象
    @PostMapping("/deleteFavorites")
    @ResponseBody
    public Map deleteFavorites(Long favoritesId){
        Map<String,String> result = new HashMap<String, String>();
        try{
            favoritesService.deleteFavoritesByFavoritesId(favoritesId);
            result.put("message","success");
        }
        catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }



}
