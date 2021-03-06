package com.ruoyi.system.service;

import com.ruoyi.system.domain.LibTag;

import java.util.List;

/**
 * 标签
 *
 * @author drebander
 */
public interface ILibTagService {
    /**
     * 标签列表
     *
     * @param tag 标签
     * @return list
     */
    List<LibTag> selectTagList(LibTag tag);

    /**
     * 根据文档id查询标签列表
     *
     * @param docId 文档id
     * @return list
     */
    List<LibTag> selectTagListByDocId(Long docId);

    /**
     * 标签名称的唯一性检查
     *
     * @param tag 标签
     * @return String
     */
    String checkTagNameUnique(LibTag tag);

    /**
     * 标签权限的唯一性检查
     *
     * @param tag 标签
     * @return String
     */
    String checkTagKeyUnique(LibTag tag);

    /**
     * 插入标签
     *
     * @param tag 标签
     * @return int
     */
    int insertTag(LibTag tag);

    LibTag selectTagById(Long tagId);

    int updateTag(LibTag tag);

    int changeStatus(LibTag tag);

    int deleteTagByIds(String ids);

    /**
     * 根据标签名称查询标签
     *
     * @param tag 查询参数
     * @return result
     */
    LibTag selectTagByName(LibTag tag);
}
