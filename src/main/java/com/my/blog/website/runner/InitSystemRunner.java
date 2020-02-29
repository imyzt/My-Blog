package com.my.blog.website.runner;

import com.my.blog.website.constant.WebConst;
import com.my.blog.website.model.vo.OptionVo;
import com.my.blog.website.service.IOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by imyzt on 2018/10/26 18:05
 * 系统初始化
 */
@Component
@Order(2)
public class InitSystemRunner implements CommandLineRunner{

    @Autowired
    private IOptionService optionService;

    @Override
    public void run(String... strings) {

        initSite();
    }

    /**
     * 初始化系统配置信息
     * @see WebConst#initConfig
     */
    private void initSite() {
        List<OptionVo> options = optionService.getOptions();
        options.forEach(optionVo -> WebConst.initConfig.put(optionVo.getName(), optionVo.getValue()));
    }
}
