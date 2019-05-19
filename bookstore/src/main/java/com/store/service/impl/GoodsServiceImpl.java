package com.store.service.impl;

import com.store.mapper.GoodsMapper;
import com.store.pojo.Goods;
import com.store.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void saveGoods(Goods goods) throws Exception {
        goodsMapper.insert(goods);
    }

    @Override
    public void updateGoods(Goods goods) {
        goodsMapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public void deleteGoods(Integer goodsId) {
        goodsMapper.deleteByPrimaryKey(goodsId);
    }

    @Override
    public Goods queryGoodsByGoodsId(Integer goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    @Override
    public List<Goods> queryGoodsListByCustomerId(Integer customerId) {
        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("customerId", customerId);
        example.orderBy("goodsId").asc();
        return goodsMapper.selectByExample(example);
    }

    @Override
    public List<Goods> queryGoodsList() {
        return goodsMapper.selectAll();
    }

    @Override
    public Goods queryGoodsByBookInfoIdAndCustomerId(Integer bookInfoId, Integer custId) {
        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bookinfoId", bookInfoId);
        criteria.andEqualTo("customerId", custId);
        List<Goods> goods = goodsMapper.selectByExample(example);
        if (goods != null && goods.size() > 0){
            return goods.get(0);
        }else {
            return null;
        }
    }
}
