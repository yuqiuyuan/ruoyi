package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.LibDoc;
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
     * 文档列表查询
     *
     * @param docTag 文档对象
     * @return list
     */
    List<LibDocTag> selectDocTagList(LibDocTag docTag);



    /**
     * 文档插入
     *
     * @param doc 文档
     * @return int
     */
    int insertDocTag(LibDocTag doc);

    /**
     * 根据文档id查询文档
     *
     * @param docId 文档id
     * @return libDoc
     */
    List<LibDocTag> selectDocTagByDocId(Long docId);

    /**
     * 文档更新
     *
     * @param docTag 文档实体
     * @return int
     */
    int updateDocTag(LibDocTag docTag);

    /**
     * 文档删除
     *
     * @param docIds 文档ids
     * @return int
     */
    int deleteDocTagByIds(Long[] docIds);
}
