package com.my.blog.website.model.Bo;

import com.my.blog.website.model.Vo.ContentVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 13 on 2017/2/23.
 */
@Data
public class ArchiveBo implements Serializable {

    private String date;
    private String count;
    private List<ContentVo> articles;

}
