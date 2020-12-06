package com.ch.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ch.pojo.User;
import org.springframework.stereotype.Repository;

@Repository //持久层
public interface UserMapper extends BaseMapper<User> {

}
