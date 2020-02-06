package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.LibDocTag;

import java.util.List;


/**
 * 文档对象映射
 *
 * @author drebander
 * @since 2020-01-28
 */
public interface LibDocTagMapper {


    /**
     * 文档插入
     *
     * @param docTag 文档标签
     * @return int
     */
    int insertDocTag(LibDocTag docTag);


    /**
     * 根据文档id删除文档标签
     *
     * @param docTag 文档标签实体
     * @return 影响条数
     */
    int updateByDocId(LibDocTag docTag);

    /**
     * 根据文档id查询文档标签列表
     *
     * @param docId 文档id
     * @return 文档标签列表
     */
    List<LibDocTag> selectDocTagByDocId(Long docId);
}
