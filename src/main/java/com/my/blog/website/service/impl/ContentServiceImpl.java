package com.my.blog.website.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.blog.website.constant.WebConst;
import com.my.blog.website.dao.ContentVoMapper;
import com.my.blog.website.dao.MetaVoMapper;
import com.my.blog.website.model.dto.Types;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.model.vo.ContentVo;
import com.my.blog.website.service.IContentService;
import com.my.blog.website.service.IMetaService;
import com.my.blog.website.service.IRelationshipService;
import com.my.blog.website.utils.DateKit;
import com.my.blog.website.utils.TaleUtils;
import com.my.blog.website.utils.Tools;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/3/13 013.
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentVoMapper, ContentVo> implements IContentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentServiceImpl.class);

    @Resource
    private ContentVoMapper contentDao;

    @Resource
    private MetaVoMapper metaDao;

    @Resource
    private IRelationshipService relationshipService;

    @Resource
    private IMetaService metasService;

    @Override
    @Transactional
    public String publish(ContentVo contents) {
        if (null == contents) {
            return "文章对象为空";
        }
        if (StringUtils.isBlank(contents.getTitle())) {
            return "文章标题不能为空";
        }
        if (StringUtils.isBlank(contents.getContent())) {
            return "文章内容不能为空";
        }
        int titleLength = contents.getTitle().length();
        if (titleLength > WebConst.MAX_TITLE_COUNT) {
            return "文章标题过长";
        }
        int contentLength = contents.getContent().length();
        if (contentLength > WebConst.MAX_TEXT_COUNT) {
            return "文章内容过长";
        }
        if (null == contents.getAuthorId()) {
            return "请登录后发布文章";
        }
        if (StringUtils.isNotBlank(contents.getSlug())) {
            if (contents.getSlug().length() < 5) {
                return "路径太短了";
            }
            if (!TaleUtils.isPath(contents.getSlug())) return "您输入的路径不合法";
            EntityWrapper<ContentVo> contentVoEntityWrapper = new EntityWrapper<>();
            contentVoEntityWrapper
                    .where("type = {0}", contents.getType())
                    .and("status = {0}", contents.getSlug());
            Integer count = contentDao.selectCount(contentVoEntityWrapper);
            if (count > 0) return "该路径已经存在，请重新输入";
        } else {
            contents.setSlug(null);
        }

        contents.setContent(EmojiParser.parseToAliases(contents.getContent()));

        int time = DateKit.getCurrentUnixTime();
        contents.setCreated(time);
        contents.setModified(time);
        contents.setHits(0);
        contents.setCommentsNum(0);

        String tags = contents.getTags();
        String categories = contents.getCategories();
        contentDao.insert(contents);
        Integer cid = contents.getCid();
        metasService.saveMetas(cid, tags, Types.TAG.getType());
        metasService.saveMetas(cid, categories, Types.CATEGORY.getType());
        return WebConst.SUCCESS_RESULT;
    }

    @Override
    public PageInfo<ContentVo> getContents(Integer p, Integer limit) {
        LOGGER.debug("Enter getContents method");
        PageHelper.startPage(p, limit);
        EntityWrapper<ContentVo> contentVoEntityWrapper = new EntityWrapper<>();
        contentVoEntityWrapper.where("type = {0} AND status = {1}", Types.ARTICLE.getType(), Types.PUBLISH.getType())
                .orderDesc(Collections.singleton("created"));
        List<ContentVo> contentVos = contentDao.selectList(contentVoEntityWrapper);
        PageInfo<ContentVo> pageInfo = new PageInfo<>(contentVos);
        LOGGER.debug("Exit getContents method");
        return pageInfo;
    }

    @Override
    public ContentVo getContents(String id) {
        if (StringUtils.isNotBlank(id)) {
            if (Tools.isNumber(id)) {
                return contentDao.selectById(Integer.valueOf(id));
            } else {
                EntityWrapper<ContentVo> contentVoEntityWrapper = new EntityWrapper<>();
                contentVoEntityWrapper.where("slug = {0}", id);
                List<ContentVo> contentVos = contentDao.selectList(contentVoEntityWrapper);
                if (contentVos.size() != 1) {
                    throw new TipException("query content by id and return is not one");
                }
                return contentVos.get(0);
            }
        }
        return null;
    }

    @Override
    public void updateContentByCid(ContentVo contentVo) {
        if (null != contentVo && null != contentVo.getCid()) {
            contentDao.updateById(contentVo);
        }
    }

    @Override
    public PageInfo<ContentVo> getArticles(Integer mid, int page, int limit) {
        int total = metaDao.countWithSql(mid);
        PageHelper.startPage(page, limit);
        List<ContentVo> list = contentDao.findByCatalog(mid);
        PageInfo<ContentVo> paginator = new PageInfo<>(list);
        paginator.setTotal(total);
        return paginator;
    }

    @Override
    public PageInfo<ContentVo> getArticles(String keyword, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);

        EntityWrapper<ContentVo> contentVoEntityWrapper = new EntityWrapper<>();
        contentVoEntityWrapper.where("type = {0} AND status = {1}", Types.ARTICLE.getType(), Types.PUBLISH.getType())
                .like("title", keyword)
                .orderDesc(Collections.singleton("created"));
        List<ContentVo> contentVos = contentDao.selectList(contentVoEntityWrapper);
        return new PageInfo<>(contentVos);
    }

    @Override
    public PageInfo<ContentVo> getArticlesWithPageAndType(Types type, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        EntityWrapper<ContentVo> contentVoEntityWrapper = new EntityWrapper<>();
        contentVoEntityWrapper.where("type = {0}", type.getType())
                .orderDesc(Collections.singleton("created"));
        List<ContentVo> contentVos = contentDao.selectList(contentVoEntityWrapper);
        return new PageInfo<>(contentVos);
    }

    @Override
    @Transactional
    public String deleteByCid(Integer cid) {
        ContentVo contents = this.getContents(cid + "");
        if (null != contents) {
            contentDao.deleteById(cid);
            relationshipService.deleteById(cid, null);
            return WebConst.SUCCESS_RESULT;
        }
        return "数据为空";
    }

    @Override
    public void updateCategory(String ordinal, String newCatefory) {
        ContentVo contentVo = new ContentVo();
        contentVo.setCategories(newCatefory);
        EntityWrapper<ContentVo> contentVoEntityWrapper = new EntityWrapper<>();
        contentVoEntityWrapper.where("categories = {0}", ordinal);
        contentDao.update(contentVo, contentVoEntityWrapper);
    }

    @Override
    @Transactional
    public String updateArticle(ContentVo contents) {
        if (null == contents) {
            return "文章对象为空";
        }
        if (StringUtils.isBlank(contents.getTitle())) {
            return "文章标题不能为空";
        }
        if (StringUtils.isBlank(contents.getContent())) {
            return "文章内容不能为空";
        }
        int titleLength = contents.getTitle().length();
        if (titleLength > WebConst.MAX_TITLE_COUNT) {
            return "文章标题过长";
        }
        int contentLength = contents.getContent().length();
        if (contentLength > WebConst.MAX_TEXT_COUNT) {
            return "文章内容过长";
        }
        if (null == contents.getAuthorId()) {
            return "请登录后发布文章";
        }
        if (StringUtils.isBlank(contents.getSlug())) {
            contents.setSlug(null);
        }
        int time = DateKit.getCurrentUnixTime();
        contents.setModified(time);
        Integer cid = contents.getCid();
        contents.setContent(EmojiParser.parseToAliases(contents.getContent()));

        contentDao.updateById(contents);
        relationshipService.deleteById(cid, null);
        metasService.saveMetas(cid, contents.getTags(), Types.TAG.getType());
        metasService.saveMetas(cid, contents.getCategories(), Types.CATEGORY.getType());
        return WebConst.SUCCESS_RESULT;
    }
}
