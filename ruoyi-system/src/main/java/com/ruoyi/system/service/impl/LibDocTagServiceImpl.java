package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.LibDocTag;
import com.ruoyi.system.mapper.LibDocTagMapper;
import com.ruoyi.system.service.ILibDocTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author drebander
 * @since 2020-02-04 9:13 PM
 **/
@Service
public class LibDocTagServiceImpl implements ILibDocTagService {


    @Resource
    private LibDocTagMapper libDocTagMapper;

    @Override
    public List<LibDocTag> selectByDocId(Long docId) {
        return libDocTagMapper.selectDocTagByDocId(docId);
    }

    @Override
    public int addTags(String tags, Long docId) {
        if (null == tags) {
            return 0;
        }
        final String[] tagArray = tags.split(",");
        int result = 0;
        for (String tagId : tagArray) {
            final Long aLong = Long.valueOf(tagId);
            LibDocTag docTag = new LibDocTag();
            docTag.setDocId(docId);
            docTag.setTagId(aLong);
            docTag.setScanCount(0);
            result = result + libDocTagMapper.insertDocTag(docTag);
        }
        return result;
    }

    @Override
    public int addTag(LibDocTag libDocTag) {
        return libDocTagMapper.insertDocTag(libDocTag);
    }

    @Override
    public int deleteByDocId(LibDocTag libDocTag) {
        libDocTag.setDelFlag("2");
        return libDocTagMapper.updateByDocId(libDocTag);
    }

    @Override
    public int update(LibDocTag libDocTag) {
        return libDocTagMapper.updateByDocId(libDocTag);
    }


    @Override
    public void updateDocTags(List<LibDocTag> libDocTags, String tags, Long docId, String userName) {
        Map<String, LibDocTag> beforeLibTag = loadLibTag(libDocTags);
        if (null != tags) {
            final String[] tagArray = tags.split(",");
            for (int i = 0; i < tagArray.length; i++) {
                if (!beforeLibTag.containsKey(tagArray[i])) {
                    LibDocTag libDocTag = new LibDocTag();
                    libDocTag.setDocId(docId);
                    libDocTag.setTagId(Long.valueOf(tagArray[i]));
                    libDocTag.setUpdateBy(userName);
                    libDocTag.setDelFlag("0");
                    addTag(libDocTag);
                } else if ("2".equals(beforeLibTag.get(tagArray[i]).getDelFlag())) {
                    final LibDocTag libDocTag = beforeLibTag.get(tagArray[i]);
                    libDocTag.setDelFlag("0");
                    libDocTag.setUpdateBy(userName);
                    update(libDocTag);
                    beforeLibTag.remove(tagArray[i]);
                } else {
                    beforeLibTag.remove(tagArray[i]);
                }
            }
            for (String tagId : beforeLibTag.keySet()) {
                final LibDocTag libDocTag = beforeLibTag.get(tagId);
                if ("0".equals(libDocTag.getDelFlag())) {
                    libDocTag.setUpdateBy(userName);
                    deleteByDocId(beforeLibTag.get(tagId));
                }
            }
        }
    }

    /**
     * 将list libDocTag组装成Map
     *
     * @param libTags 文档标签列表
     * @return 文档标签映射
     */
    private Map<String, LibDocTag> loadLibTag(List<LibDocTag> libTags) {
        Map<String, LibDocTag> objectObjectHashMap = new HashMap<>(libTags.size());
        libTags.forEach(libTag ->
                objectObjectHashMap.put(String.valueOf(libTag.getTagId()), libTag));
        return objectObjectHashMap;
    }

}
