package com.my.blog.website.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.my.blog.website.model.bo.ArchiveBo;
import com.my.blog.website.model.vo.ContentVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentVoMapper extends BaseMapper<ContentVo> {

    List<ArchiveBo> findReturnArchiveBo();

    List<ContentVo> findByCatalog(Integer mid);
}