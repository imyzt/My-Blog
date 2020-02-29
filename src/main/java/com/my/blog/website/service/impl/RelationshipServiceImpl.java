package com.my.blog.website.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.my.blog.website.model.vo.RelationshipVoKey;
import com.my.blog.website.service.IRelationshipService;
import com.my.blog.website.dao.RelationshipVoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by BlueT on 2017/3/18.
 */
@Service
public class RelationshipServiceImpl extends ServiceImpl<RelationshipVoMapper, RelationshipVoKey> implements IRelationshipService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RelationshipServiceImpl.class);

    @Resource
    private RelationshipVoMapper relationshipVoMapper;

    @Override
    public void deleteById(Integer cid, Integer mid) {

        EntityWrapper<RelationshipVoKey> wrapper = wrapper(cid, mid);
        relationshipVoMapper.delete(wrapper);
    }

    @Override
    public List<RelationshipVoKey> getRelationshipById(Integer cid, Integer mid) {

        EntityWrapper<RelationshipVoKey> wrapper = wrapper(cid, mid);

        return relationshipVoMapper.selectList(wrapper);
    }

    @Override
    public void insertVo(RelationshipVoKey relationshipVoKey) {
        relationshipVoMapper.insert(relationshipVoKey);
    }

    @Override
    public Long countById(Integer cid, Integer mid) {
        LOGGER.debug("Enter countById method:cid={},mid={}",cid,mid);

        EntityWrapper<RelationshipVoKey> wrapper = wrapper(cid, mid);

        long num = relationshipVoMapper.selectCount(wrapper);
        LOGGER.debug("Exit countById method return num={}",num);
        return num;
    }

    /**
     * 构造查询参数
     * @param cid
     * @param mid
     * @return
     */
    private EntityWrapper<RelationshipVoKey> wrapper(Integer cid, Integer mid) {
        EntityWrapper<RelationshipVoKey> wrapper = new EntityWrapper<>();
        wrapper.where("1=1");
        if (cid != null) {
            wrapper.and("cid = {0}", cid);
        }
        if (mid != null) {
            wrapper.and("mid = {0}", mid);
        }
        return wrapper;
    }
}
