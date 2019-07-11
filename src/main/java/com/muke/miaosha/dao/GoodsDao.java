package com.muke.miaosha.dao;

import com.muke.miaosha.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yesyoungbaby
 * @Title: GoodsDao
 * @ProjectName miaosha
 * @Description: TODO
 * @date 2019/7/1121:01
 */
@Mapper
public interface GoodsDao {

    @Select("select g.*,mg.stock_count,mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id")
    public List<GoodsVo> getGoodsVoList();
}
