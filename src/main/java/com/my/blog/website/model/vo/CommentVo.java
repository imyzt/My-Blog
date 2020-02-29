package com.my.blog.website.model.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 
 */
@TableName("t_comments")
@Data
public class CommentVo implements Serializable {
    /**
     * comment表主键
     */
    @TableId(value = "coid", type = IdType.AUTO)
    private Integer coid;

    /**
     * post表主键,关联字段
     */
    private Integer cid;

    /**
     * 评论生成时的GMT unix时间戳
     */
    private Integer created;

    /**
     * 评论作者
     */
    private String author;

    /**
     * 评论所属用户id
     */
    private Integer authorId;

    /**
     * 评论所属内容作者id
     */
    private Integer ownerId;

    /**
     * 评论者邮件
     */
    private String mail;

    /**
     * 评论者网址
     */
    private String url;

    /**
     * 评论者ip地址
     */
    private String ip;

    /**
     * 评论者客户端
     */
    private String agent;

    /**
     * 评论类型
     */
    private String type;

    /**
     * 评论状态
     */
    private String status;

    /**
     * 父级评论
     */
    private Integer parent;

    /**
     * 评论内容
     */
    private String content;

    private static final long serialVersionUID = 1L;
}