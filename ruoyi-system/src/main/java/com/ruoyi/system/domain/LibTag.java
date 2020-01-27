package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName LibTag
 * @Description 资料库标签实体
 * @Author drebander
 * @Date 2020-01-25 4:24 PM
 * @Version 1.0
 **/
@Data
public class LibTag extends BaseEntity {
    /**
     * 标签ID
     */
    @Excel(name = "标签序号", cellType = Excel.ColumnType.NUMERIC)
    private Long tagId;
    /**
     * 标签名称
     */
    @Excel(name = "角色序号", cellType = Excel.ColumnType.STRING)
    private String tagName;
    /**
     * 标签权限字段
     */
    @Excel(name = "标签权限", cellType = Excel.ColumnType.STRING)
    private String tagKey;
    /**
     * 显示顺序
     */
    @Excel(name = "显示顺序", cellType = Excel.ColumnType.NUMERIC)
    private int tagSort;
    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本标签数据权限 4：本标签及以下数据权限）
     */
    @Excel(name = "数据范围", cellType = Excel.ColumnType.STRING)
    private String dataScope;
    /**
     * 标签状态（0正常 1停用）
     */
    @Excel(name = "标签状态", cellType = Excel.ColumnType.STRING)
    private String tagStatus;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @Excel(name = "删除标志", cellType = Excel.ColumnType.STRING)
    private String delFlag;
    /**
     * 浏览次数
     */
    @Excel(name = "浏览次数", cellType = Excel.ColumnType.NUMERIC)
    private int scanCount;
    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 备注
     */
    @Excel(name = "备注", cellType = Excel.ColumnType.STRING)
    private String remark;
}
