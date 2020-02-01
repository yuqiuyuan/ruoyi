package com.ruoyi.system.service;

import com.ruoyi.system.domain.LibDoc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ILibDocService {

    List<LibDoc> selectDocList(LibDoc doc);

    String checkDocNameUnique(LibDoc doc);

    @Transactional
    int insertDoc(LibDoc doc);

    LibDoc selectDocById(Long DocId);

    @Transactional
    int updateDoc(LibDoc doc);

    @Transactional
    int changeStatus(LibDoc doc);

    int deleteDocByIds(String ids);
}
