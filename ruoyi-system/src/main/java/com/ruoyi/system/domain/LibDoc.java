package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName LibDoc
 * @Description 文档实体
 * @Author drebander
 * @Date 2020-01-28 9:31 AM
 * @Version 1.0
 **/
@Data
public class LibDoc extends BaseEntity {
    /**
     * 文档ID
     */
    @Excel(name = "文档序号", cellType = Excel.ColumnType.NUMERIC)
    private Long docId;

    /**
     * 文档标题
     */
    private String docName;
    /**
     * 文档类型
     */
    private String docType;
    /**
     * 文档图片
     */
    private String docImg;
    /**
     * 文档地址
     */
    private String docUrl;
    /**
     * 文档状态
     */
    private String docStatus;
    /**
     * 删除标识
     */
    private String delFlag;
    /**
     * 浏览次数
     */
    private int scanCount;
    /**
     * 上传人
     */
    private String createBy;
    /**
     * 上传时间
     */
    private Date createTime;
    /**
     * 最近更新人
     */
    private String updateBy;
    /**
     * 最近更新时间
     */
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;

    /**
     * 文档顺序
     */
    private int docSort;
}
