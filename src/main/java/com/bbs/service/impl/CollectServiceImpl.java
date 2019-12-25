package com.bbs.service.impl;

import com.bbs.entity.Collect;
import com.bbs.repository.ICollectRepository;
import com.bbs.repository.IPostRepository;
import com.bbs.service.ICollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Service
public class CollectServiceImpl implements ICollectService {

    private ICollectRepository collectRepository;
    private IPostRepository postRepository;

    @Autowired
    public void setCollectRepository(ICollectRepository collectRepository){
        this.collectRepository = collectRepository;
    }

    @Autowired
    public void setPostRepository(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public void createCollect(Collect collect){
        postRepository.collectByPostId(collect.getPostId());
        collectRepository.createCollect(collect);
    }

    @Override
    public void updateCollect(Long postId,Long userId, Long favoritesId){
        collectRepository.updateCollect(postId,userId,favoritesId);
    }

    @Override
    public List<Collect> findAllCollectsByFavoritesId(Long favoritesId){
        return collectRepository.findAllCollectsByFavoritesId(favoritesId);
    }

    @Override
    @Transactional
    public void deleteCollect(Long postId,Long userId){
        postRepository.uncollectByPostId(postId);
        collectRepository.deleteCollect(postId,userId);
    }

    @Override
    public Boolean isCollectPostByUserId(Long postId, Long userId) {
        return collectRepository.isCollectPostByUserId(postId, userId);
    }
}
