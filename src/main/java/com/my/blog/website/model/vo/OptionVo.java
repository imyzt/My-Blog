package com.my.blog.website.model.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 
 */
@TableName("t_options")
@Data
public class OptionVo implements Serializable {
    /**
     * 配置名称
     */
    @TableId(value = "name", type = IdType.INPUT)
    private String name;

    /**
     * 配置值
     */
    private String value;

    private String description;

    private static final long serialVersionUID = 1L;
}