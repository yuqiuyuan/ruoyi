package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.LibDoc;

import java.util.List;

/**
 * 文档对象映射
 *
 * @author drebander
 * @since 2020-01-28
 */
public interface LibDocMapper {
    /**
     * 文档列表查询
     *
     * @param doc 文档对象
     * @return list
     */
    List<LibDoc> selectDocList(LibDoc doc);

    /**
     * 文档名称的唯一性校验
     *
     * @param docName 文档标题
     * @return libDoc
     */
    LibDoc checkDocNameUnique(String docName);

    /**
     * 文档插入
     *
     * @param doc 文档
     * @return int
     */
    int insertDoc(LibDoc doc);

    /**
     * 根据文档id查询文档
     *
     * @param docId 文档id
     * @return libDoc
     */
    LibDoc selectDocById(Long docId);

    /**
     * 文档更新
     *
     * @param doc 文档实体
     * @return int
     */
    int updateDoc(LibDoc doc);

    /**
     * 文档删除
     *
     * @param docIds 文档ids
     * @return int
     */
    int deleteDocByIds(Long[] docIds);
}
