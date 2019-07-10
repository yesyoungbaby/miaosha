package com.muke.miaosha.dao;

import com.muke.miaosha.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author yesyoungbaby
 * @Title: MiaoshaUserDao
 * @ProjectName miaosha
 * @Description: TODO
 * @date 2019/6/2611:41
 */
@Mapper
public interface MiaoshaUserDao {
    @Select("select * from miaosha_user where id = #{id}")
    public MiaoshaUser getById(@Param("id")long id);
}
