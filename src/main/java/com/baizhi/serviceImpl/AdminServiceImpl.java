package com.baizhi.serviceImpl;

import com.baizhi.entity.Admin;
import com.baizhi.mapper.AdminMapper;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, String> adminLogin(String username, String password, String enCode, HttpSession session) {
        Map<String, String> map = new HashMap<>();
        session.setAttribute("username", username);
        String code = (String) session.getAttribute("code");
        if (code.equals(enCode)) {
            Admin admin = adminMapper.adminLogin(username);
            if (admin != null) {
                if (admin.getPassword().equals(password)) {
                    map.put("message", "ok");
                } else {
                    map.put("message", "密码不正确");
                }
            } else {
                map.put("message", "此用户不存在");
            }
        } else {
            map.put("message", "验证码错误");
        }
        return map;
    }
}
