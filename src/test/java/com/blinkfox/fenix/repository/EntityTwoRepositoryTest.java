package com.blinkfox.fenix.repository;

import com.blinkfox.fenix.FenixTestApplication;
import com.blinkfox.fenix.entity.EntityTwo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基于 {@link EntityTwoRepository} 用来测试自定义 ID 生成策略功能的单元测试类.
 *
 * @author blinkfox on 2020-12-07.
 * @since v2.4.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FenixTestApplication.class)
public class EntityTwoRepositoryTest {

    @Resource
    private EntityTwoRepository entityTwoRepository;

    /**
     * 测试保存所有数据的方法.
     */
    @Test
    public void saveAll() {
        // 测试单条插入.
        EntityTwo entity = this.entityTwoRepository.save(new EntityTwo("标题"));
        Assert.assertNotNull(entity.getId());

        // 测试批量保存数据时的 ID 生成.
        List<EntityTwo> entityTwos = new ArrayList<>();
        for (int i = 0; i < 2; ++i) {
            entityTwos.add(new EntityTwo("标题" + (i + 1)));
        }
        this.entityTwoRepository.saveAll(entityTwos);
        List<EntityTwo> twos = this.entityTwoRepository.findAll();
        Assert.assertTrue(twos.size() > 2);
        for (EntityTwo entityTwo : twos) {
            Assert.assertNotNull(entityTwo.getId());
        }
    }

}