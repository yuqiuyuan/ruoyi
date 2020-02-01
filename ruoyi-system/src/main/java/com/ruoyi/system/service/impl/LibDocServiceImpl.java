package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.LibDoc;
import com.ruoyi.system.mapper.LibDocMapper;
import com.ruoyi.system.service.ILibDocService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName LibDocServiceImpl
 * @Description 标签服务
 * @Author drebander
 * @Date 2020-01-25 4:47 PM
 * @Version 1.0
 **/
@Service
public class LibDocServiceImpl implements ILibDocService {

    @Resource
    private LibDocMapper libDocMapper;

    @Override
    public List<LibDoc> selectDocList(LibDoc doc) {
        return libDocMapper.selectDocList(doc);
    }

    @Override
    public String checkDocNameUnique(LibDoc doc) {
        Long DocId = StringUtils.isNull(doc.getDocId()) ? -1L : doc.getDocId();
        LibDoc info = libDocMapper.checkDocNameUnique(doc.getDocName());
        if (StringUtils.isNotNull(info) && info.getDocId().longValue() != DocId.longValue()) {
            return UserConstants.ROLE_NAME_NOT_UNIQUE;
        }
        return UserConstants.ROLE_NAME_UNIQUE;
    }

    @Override
    @Transactional
    public int insertDoc(LibDoc doc) {
        return libDocMapper.insertDoc(doc);
    }

    @Override
    public LibDoc selectDocById(Long DocId) {
        return libDocMapper.selectDocById(DocId);
    }

    @Override
    @Transactional
    public int updateDoc(LibDoc doc) {
        // 修改标签信息
        return libDocMapper.updateDoc(doc);
    }

    @Override
    @Transactional
    public int changeStatus(LibDoc doc) {
        return libDocMapper.updateDoc(doc);
    }

    @Override
    public int deleteDocByIds(String ids) {
        Long[] DocIds = Convert.toLongArray(ids);
        return libDocMapper.deleteDocByIds(DocIds);
    }

}
