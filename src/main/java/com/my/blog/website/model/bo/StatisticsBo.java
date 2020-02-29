package com.my.blog.website.model.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 后台统计对象
 */
@Data
public class StatisticsBo implements Serializable {

    private Integer articles;
    private Integer comments;
    private Integer links;
    private Integer attachs;
}
