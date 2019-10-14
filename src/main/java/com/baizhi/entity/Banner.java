package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner {
    @Excel(name = "id", width = 40)
    private String id;
    @Excel(name = "标题", width = 20, needMerge = true)
    private String title;
    @Excel(name = "状态", replace = {"展示_y", "不展示_n"})
    private String status;
    @Excel(name = "描述信息", width = 50)
    private String descc;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", format = "yyyy-MM-dd", width = 20)
    private Date createDate;
    @Excel(name = "图片", type = 2)
    private String imgPath;
}
