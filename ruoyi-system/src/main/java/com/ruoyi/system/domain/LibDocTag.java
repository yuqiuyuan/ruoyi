package com.ruoyi.system.domain;

import lombok.Data;

import java.util.Date;

/**
 * 文档标签实体类
 *
 * @author drebander
 * @since 2020-01-29
 */
@Data
public class LibDocTag {

    /**
     * 文档标签id
     */
    private Long docId;
    /**
     * 标签id
     */
    private Long tagId;
    /**
     * 浏览次数
     */
    private int scanCount;
    /**
     * 更新时间
     */
    private Date updateTime;
}
