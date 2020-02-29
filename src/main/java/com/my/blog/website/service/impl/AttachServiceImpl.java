package com.my.blog.website.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.blog.website.dao.AttachVoMapper;
import com.my.blog.website.utils.DateKit;
import com.my.blog.website.model.vo.AttachVo;
import com.my.blog.website.service.IAttachService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangq on 2017/3/20.
 */
@Service
public class AttachServiceImpl extends ServiceImpl<AttachVoMapper, AttachVo> implements IAttachService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AttachServiceImpl.class);

    @Resource
    private AttachVoMapper attachDao;

    @Override
    public PageInfo<AttachVo> getAttachs(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        EntityWrapper<AttachVo> attachVoEntityWrapper = new EntityWrapper<>();
        List<AttachVo> attachVos = attachDao.selectList(attachVoEntityWrapper);
        return new PageInfo<>(attachVos);
    }

    @Override
    public AttachVo selectById(Integer id) {
        if(null != id){
            return attachDao.selectById(id);
        }
        return null;
    }

    @Override
    @Transactional
    public void save(String fname, String fkey, String ftype, Integer author) {
        AttachVo attach = new AttachVo();
        attach.setFname(fname);
        attach.setAuthorId(author);
        attach.setFkey(fkey);
        attach.setFtype(ftype);
        attach.setCreated(DateKit.getCurrentUnixTime());
        attachDao.insert(attach);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (null != id) {
            attachDao.deleteById( id);
        }
    }
}
