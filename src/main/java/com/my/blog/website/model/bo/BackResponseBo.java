package com.my.blog.website.model.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by 13 on 2017/2/25.
 */
@Data
public class BackResponseBo implements Serializable {

    private String attachPath;
    private String themePath;
    private String sqlPath;

}
