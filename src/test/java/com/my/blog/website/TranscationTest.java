package com.my.blog.website;

import com.my.blog.website.model.vo.UserVo;
import com.my.blog.website.service.IUserService;
import com.my.blog.website.service.IOptionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 测试数据库事务
 * Created by BlueT on 2017/3/8.
 */
@MapperScan("com.my.blog.website.dao")
@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional(rollbackFor = TipException.class)
public class TranscationTest {

    @Resource
    private IUserService userService;

    @Resource
    private IOptionService optionService;

    @Test
//    @Ignore
    public void test() {
        UserVo user = new UserVo();
        user.setUsername("");
        user.setPassword("");
        user.setEmail("8888");
        userService.insertUser(user);
        optionService.insertOption("site_keywords", "qwqwq");
    }
}
