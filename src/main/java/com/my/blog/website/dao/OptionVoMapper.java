package com.my.blog.website.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.my.blog.website.model.vo.OptionVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionVoMapper extends BaseMapper<OptionVo> {

    /**
     * 批量保存
     * @param optionVos list
     * @return 保存的个数
     */
    int insertOptions(List<OptionVo> optionVos);
}