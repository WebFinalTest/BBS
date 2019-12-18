package com.bbs;

import com.bbs.entity.Collect;
import com.bbs.service.ICollectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectServiceImplTest {
    ICollectService collectService ;

    @Autowired
    public void setCollectService(ICollectService collectService) {
        this.collectService = collectService;
    }

    @Test
    public void Test(){

        Collect collect = new Collect();
        collect.setPostId(1L);
        collect.setFavoritesId(3L);
        collect.setUserId(1L);
        collectService.createCollect(collect);

        collectService.updateCollect(1L,1L,6L);

        List<Collect> a = collectService.findAllCollectsByFavoritesId(6L);

        System.out.println(a.get(0).getPostId());

        collectService.deleteCollect(1L,1L);
    }

}
