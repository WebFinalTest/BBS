package com.bbs.service.impl;

import com.bbs.entity.Collect;
import com.bbs.repository.ICollectRepository;
import com.bbs.service.ICollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements ICollectService {

    private ICollectRepository collectRepository;

    @Autowired
    public void setCollectRepository(ICollectRepository collectRepository){
        this.collectRepository = collectRepository;
    }

    @Override
    public void createCollect(Collect collect){
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
    public void deleteCollect(Long postId,Long userId){
        collectRepository.deleteCollect(postId,userId);
    }
}
