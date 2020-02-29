package com.my.blog.website.service;

import com.baomidou.mybatisplus.service.IService;
import com.my.blog.website.model.vo.UserVo;

/**
 * Created by BlueT on 2017/3/3.
 */
public interface IUserService extends IService<UserVo> {

    /**
     * 保存用户数据
     *
     * @param userVo 用户数据
     * @return 主键
     */

    Integer insertUser(UserVo userVo);

    /**
     * 通过uid查找对象
     * @param uid
     * @return
     */
    UserVo queryUserById(Integer uid);

    /**
     * 用戶登录
     * @param username
     * @param password
     * @return
     */
    UserVo login(String username, String password);

    /**
     * 根据主键更新user对象
     * @param userVo
     * @return
     */
    void updateByUid(UserVo userVo);
}
