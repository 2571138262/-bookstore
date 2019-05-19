package com.store.service;

import com.store.pojo.Goods;

import java.util.List;

public interface GoodsService {

    void saveGoods(Goods goods) throws Exception;

    void updateGoods(Goods goods);

    void deleteGoods(Integer goodsId);

    Goods queryGoodsByGoodsId(Integer goodsId);
    
    Goods queryGoodsByBookInfoIdAndCustomerId(Integer bookInfoId, Integer custId);

    List<Goods> queryGoodsListByCustomerId(Integer customerId);

    List<Goods> queryGoodsList();
    
}
