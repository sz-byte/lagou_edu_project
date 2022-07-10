package com.lagou.service.impl;

import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.domain.PromotionSpace;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {
    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    @Override
    public List<PromotionSpace> findAllPromotionSpace() {

        List<PromotionSpace> allPromotionSpace = promotionSpaceMapper.finAllPromotionSpace();
        return allPromotionSpace;
    }

    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {
        Date date = new Date();
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);

        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    /*
    * 根据id查询广告位信息
    * */
    @Override
    public PromotionSpace findPromotionSpaceById(int id) {
        PromotionSpace promotionSpaceById = promotionSpaceMapper.findPromotionSpaceById(id);
        return promotionSpaceById;
    }

    /*
    * 修改广告位名称
    * */
    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        promotionSpace.setUpdateTime(new Date());

        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }
}
