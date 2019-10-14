package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String headImg;
    private String dharma;
    private String name;
    private String sex;
    private String province;
    private String city;
    private String sign;
    private String username;
    private String password;
    private String status;
    private Date createDate;
}
