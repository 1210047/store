package com.nkb.store.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nkb.store.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;


public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(String username);

    Integer updateAvatarByUid(Integer uid, String avatar, String username, Date now);
}
