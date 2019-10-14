package com.baizhi.serviceImpl;

import com.baizhi.entity.UserMap;
import com.baizhi.mapper.UserMapper;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserMap> findByProvince() {
        List<UserMap> byProvince = userMapper.findByProvince();
        return byProvince;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Integer> findByTime() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            Integer time = userMapper.findByTime(i);
            list.add(time);
        }
        return list;
    }
}
