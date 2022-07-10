package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

import java.util.List;

public interface PromotionAdService {


    /*
     * 分页查询广告信息
     * */
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO);

    /*
    * 广告动态上下线
    * */
    public void updatePromotionAdStatus(int id,int status);

    /*
     * 新增广告信息
     * */
    public void savePromotion(PromotionAd promotionAd);

    /*
     * 修改广告信息
     * */
    public void updatePromotion(PromotionAd promotionAd);

    /*
     * 根据id查询广告
     * */
    PromotionAd findPromotionAdById(int id);

}
