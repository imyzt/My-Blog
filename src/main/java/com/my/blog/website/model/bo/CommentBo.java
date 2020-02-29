package com.my.blog.website.model.bo;

import com.my.blog.website.model.vo.CommentVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 返回页面的评论，包含父子评论内容
 * Created by 13 on 2017/2/24.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentBo extends CommentVo {

    private int levels;
    private List<CommentVo> children;

    public CommentBo(CommentVo comments) {
        setAuthor(comments.getAuthor());
        setMail(comments.getMail());
        setCoid(comments.getCoid());
        setAuthorId(comments.getAuthorId());
        setUrl(comments.getUrl());
        setCreated(comments.getCreated());
        setAgent(comments.getAgent());
        setIp(comments.getIp());
        setContent(comments.getContent());
        setOwnerId(comments.getOwnerId());
        setCid(comments.getCid());
    }
}
