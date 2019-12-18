package com.bbs;

import com.bbs.entity.Collect;
import com.bbs.repository.ICollectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectRepositoryTest {
    private ICollectRepository collectRepository;

    @Autowired
    public void setCollectRepository(ICollectRepository collectRepository){
        this.collectRepository = collectRepository;
    }

    @Test
    public void test(){
        //创建一个评论
        Collect collect = new Collect();
        collect.setPostId( 1L );
        collect.setUserId( 1L );
        collect.setFavoritesId( 3L );
        collectRepository.createCollect(collect);
        System.out.println("3");

        collectRepository.updateCollect(1L,1L,12L);

        List<Collect> a = collectRepository.findAllCollectsByFavoritesId(12L);
        System.out.println(3);


    }
}




