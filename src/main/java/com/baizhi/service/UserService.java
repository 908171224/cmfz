package com.baizhi.service;

import com.baizhi.entity.UserMap;

import java.util.List;

public interface UserService {
    public List<UserMap> findByProvince();

    public List<Integer> findByTime();
}
