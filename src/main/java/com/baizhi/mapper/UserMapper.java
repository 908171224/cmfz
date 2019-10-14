package com.baizhi.mapper;


import com.baizhi.entity.UserMap;

import java.util.List;

public interface UserMapper {
    public List<UserMap> findByProvince();

    public Integer findByTime(Integer day);
}
