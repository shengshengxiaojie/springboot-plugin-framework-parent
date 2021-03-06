package com.mybatisplus.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatisplus.main.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description
 *
 * @author zhangzhuo
 * @version 1.0
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 得到用户列表
     * @return List
     */
    List<User> getList();

    /**
     * 通过id得到用户
     * @param id id
     * @return User
     */
    @Select("select * from user where user_id = #{id}")
    User getById(@Param("id") String id);

}
