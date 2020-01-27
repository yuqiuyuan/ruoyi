package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.LibTag;

import java.util.List;

public interface LibTagMapper {

    /**
     * 根据条件分页查询标签数据
     *
     * @param tag 标签信息
     * @return 标签数据集合信息
     */
    List<LibTag> selectTagList(LibTag tag);

    /**
     * 标签名称的唯一性检查
     *
     * @param tagName 标签名称
     * @return LibTag
     */
    LibTag checkTagNameUnique(String tagName);

    /**
     * 标签权限字段唯一性校验
     *
     * @param tagKey 标签权限
     * @return LibTag
     */
    LibTag checkTagKeyUnique(String tagKey);

    /**
     * 插入标签
     *
     * @param tag 标签
     */
    int insertTag(LibTag tag);

    LibTag selectTagById(Long tagId);

    int updateTag(LibTag tag);

    int deleteTagByIds(Long[] tagIds);
}
