package com.my.blog.website.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.my.blog.website.model.dto.MetaDto;
import com.my.blog.website.model.vo.MetaVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MetaVoMapper extends BaseMapper<MetaVo> {

    List<MetaDto> selectFromSql(Map<String,Object> paraMap);

    MetaDto selectDtoByNameAndType(@Param("name") String name,@Param("type") String type);

    Integer countWithSql(Integer mid);
}