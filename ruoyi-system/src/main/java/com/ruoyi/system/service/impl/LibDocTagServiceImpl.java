package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.LibDocTag;
import com.ruoyi.system.mapper.LibDocTagMapper;
import com.ruoyi.system.service.ILibDocTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author drebander
 * @since 2020-02-04 9:13 PM
 **/
@Service
public class LibDocTagServiceImpl implements ILibDocTagService {


    @Resource
    private LibDocTagMapper libDocTagMapper;

    @Override
    public void addTags(String tags, Long docId) {
        if (null == tags) {
            return;
        }
        final String[] tagArray = tags.split(",");
        for (String tagId : tagArray) {
            final Long aLong = Long.valueOf(tagId);
            LibDocTag docTag = new LibDocTag();
            docTag.setDocId(docId);
            docTag.setTagId(aLong);
            docTag.setScanCount(0);
            libDocTagMapper.insertDocTag(docTag);
        }
    }
}
