package com.my.blog.website.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.my.blog.website.dao.UserVoMapper;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.model.vo.UserVo;
import com.my.blog.website.service.IUserService;
import com.my.blog.website.utils.DateKit;
import com.my.blog.website.utils.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by BlueT on 2017/3/3.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserVoMapper, UserVo> implements IUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserVoMapper userDao;

    @Override
    @Transactional
    public Integer insertUser(UserVo userVo) {
        Integer uid = null;
        if (StringUtils.isNotBlank(userVo.getUsername()) && StringUtils.isNotBlank(userVo.getEmail())) {
//            用户密码加密
            String encodePwd = TaleUtils.MD5encode(userVo.getUsername() + userVo.getPassword());
            userVo.setPassword(encodePwd);
            userVo.setCreated(DateKit.currentTime());
            userDao.insert(userVo);
        }
        return userVo.getUid();
    }

    @Override
    public UserVo queryUserById(Integer uid) {
        UserVo userVo = null;
        if (uid != null) {
            userVo = userDao.selectById(uid);
        }
        return userVo;
    }

    @Override
    public UserVo login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new TipException("用户名和密码不能为空");
        }
        EntityWrapper<UserVo> userVoEntityWrapper = new EntityWrapper<>();
        userVoEntityWrapper.where("username = {0}", username);
        Integer count = userDao.selectCount(userVoEntityWrapper);
        if (count < 1) {
            throw new TipException("不存在该用户");
        }
        String pwd = TaleUtils.MD5encode(username + password);
        userVoEntityWrapper.and("password = {0}", pwd);
        List<UserVo> userVos = userDao.selectList(userVoEntityWrapper);
        if (userVos.size() != 1) {
            throw new TipException("用户名或密码错误");
        }
        return userVos.get(0);
    }

    @Override
    @Transactional
    public void updateByUid(UserVo userVo) {
        if (null == userVo || null == userVo.getUid()) {
            throw new TipException("userVo is null");
        }
        int i = userDao.updateById(userVo);
        if (i != 1) {
            throw new TipException("update user by uid and retrun is not one");
        }
    }
}
