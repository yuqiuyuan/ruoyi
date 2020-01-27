package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.LibTag;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.mapper.LibTagMapper;
import com.ruoyi.system.service.ILibTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName LibTagServiceImpl
 * @Description 标签服务
 * @Author drebander
 * @Date 2020-01-25 4:47 PM
 * @Version 1.0
 **/
@Service
public class LibTagServiceImpl implements ILibTagService {

    @Resource
    private LibTagMapper libTagMapper;

    @Override
    public List<LibTag> selectTagList(LibTag tag) {
        return libTagMapper.selectTagList(tag);
    }

    @Override
    public String checkTagNameUnique(LibTag tag) {
        Long tagId = StringUtils.isNull(tag.getTagId()) ? -1L : tag.getTagId();
        LibTag info = libTagMapper.checkTagNameUnique(tag.getTagName());
        if (StringUtils.isNotNull(info) && info.getTagId().longValue() != tagId.longValue()) {
            return UserConstants.ROLE_NAME_NOT_UNIQUE;
        }
        return UserConstants.ROLE_NAME_UNIQUE;
    }

    @Override
    public String checkTagKeyUnique(LibTag tag) {
        Long tagId = StringUtils.isNull(tag.getTagId()) ? -1L : tag.getTagId();
        LibTag info = libTagMapper.checkTagKeyUnique(tag.getTagKey());
        if (StringUtils.isNotNull(info) && info.getTagId().longValue() != tagId.longValue()) {
            return UserConstants.ROLE_KEY_NOT_UNIQUE;
        }
        return UserConstants.ROLE_KEY_UNIQUE;
    }

    @Override
    @Transactional
    public int insertTag(LibTag tag) {
        return libTagMapper.insertTag(tag);
    }

    @Override
    public LibTag selectTagById(Long tagId) {
        return libTagMapper.selectTagById(tagId);
    }

    @Override
    @Transactional
    public int updateTag(LibTag tag) {
        // 修改标签信息
        return libTagMapper.updateTag(tag);
    }

    @Override
    @Transactional
    public int changeStatus(LibTag tag) {
        return libTagMapper.updateTag(tag);
    }

    @Override
    public int deleteTagByIds(String ids) {
        Long[] tagIds = Convert.toLongArray(ids);
        return libTagMapper.deleteTagByIds(tagIds);
    }

}
