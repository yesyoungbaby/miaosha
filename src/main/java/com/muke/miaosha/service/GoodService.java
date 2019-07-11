package com.muke.miaosha.service;

import com.muke.miaosha.dao.GoodsDao;
import com.muke.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yesyoungbaby
 * @Title: GoodService
 * @ProjectName miaosha
 * @Description: TODO
 * @date 2019/7/1120:59
 */
@Service
public class GoodService {
    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVo>  listGoodsVo(){
        return goodsDao.getGoodsVoList();
    }
}
