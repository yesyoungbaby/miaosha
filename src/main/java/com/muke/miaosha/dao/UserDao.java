package com.muke.miaosha.dao;

import com.muke.miaosha.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author yesyoungbaby
 * @Title: UserDao
 * @ProjectName miaosha
 * @Description: TODO
 * @date 2019/6/2316:18
 */
@Mapper
public interface UserDao {

    @Select("select * from user where id = #{id}")
    public User getById(@Param("id") int id);

    @Insert("insert into user(id, name)values(#{id}, #{name})")
    public int insert(User user);
}
