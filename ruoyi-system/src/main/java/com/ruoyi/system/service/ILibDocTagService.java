package com.ruoyi.system.service;

import com.ruoyi.system.domain.LibDocTag;
import com.ruoyi.system.domain.LibTag;

import java.util.List;

/**
 * 文档标签服务
 *
 * @author drebander
 * @since 2020-02-06
 */
public interface ILibDocTagService {


    /**
     * 根据文档id查找文章标签
     *
     * @param docId 文档id
     * @return 文档标签列表
     */
    List<LibDocTag> selectByDocId(Long docId);

    /**
     * 添加文档标签
     *
     * @param tags  标签
     * @param docId 文档id
     * @return 影响条数
     */
    int addTags(String tags, Long docId);

    /**
     * 添加文档标签
     *
     * @param libDocTag 文档标签
     * @return 影响条数
     */
    int addTag(LibDocTag libDocTag);

    /**
     * 根据文档id删除文档标签
     *
     * @param libDocTag 文档id
     * @return 影响条数
     */
    int deleteByDocId(LibDocTag libDocTag);

    /**
     * 更新文档标签
     *
     * @param libDocTags 更新前的标签列表
     * @param tags       新更新的标签
     * @param docId      文档id
     * @param userName   用户名称
     */
    void updateDocTags(List<LibDocTag> libDocTags, String tags, Long docId, String userName);

    /**
     * 更新操作
     *
     * @param libDocTag 文档标签
     * @return 影响条数
     */
    int update(LibDocTag libDocTag);
}
