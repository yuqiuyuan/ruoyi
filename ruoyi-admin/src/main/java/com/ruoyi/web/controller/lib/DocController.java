package com.ruoyi.web.controller.lib;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.ILibDocService;
import com.ruoyi.system.service.ILibDocTagService;
import com.ruoyi.system.service.ILibTagService;
import com.ruoyi.web.core.PdfHelper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 文档管理控制器
 *
 * @author drebander
 * @since 2020-01-27
 */

@Controller
@RequestMapping("/lib/doc")
public class DocController extends BaseController {

    private static String PREFIX = "lib/doc";
    private final ILibDocService libDocService;
    private final ILibTagService libTagService;
    private final ILibDocTagService iLibDocTagService;
    @Resource
    private ServerConfig serverConfig;

    @Autowired
    public DocController(ILibDocService libDocService, ILibTagService libTagService, ILibDocTagService iLibDocTagService) {
        this.libDocService = libDocService;
        this.libTagService = libTagService;
        this.iLibDocTagService = iLibDocTagService;
    }

    @RequiresPermissions("lib:doc:view")
    @GetMapping()
    public String doc(ModelMap mmap) {
        return PREFIX + "/doc";
    }

    @RequiresPermissions("lib:doc:view")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LibDoc doc) {
        startPage();
        List<LibDoc> list = libDocService.selectDocList(doc);
        return getDataTable(list);
    }

    /**
     * 新增文档
     */
    @RequiresRoles(value = {"develop", "operator"}, logical = Logical.OR)
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        final ArrayList<Map<String, java.io.Serializable>> objects = new ArrayList<>(2);
        mmap.put("tagList", objects);
        return PREFIX + "/add";
    }

    /**
     * 新增保存文档
     */
    @RequiresRoles(value = {"develop", "operator"}, logical = Logical.OR)
    @Log(title = "文档管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    @Transactional
    public AjaxResult addSave(@Validated LibDocModel docModel) {
        LibDoc libDoc = generateLibDocByModel(docModel);
        if (UserConstants.ROLE_NAME_NOT_UNIQUE.equals(libDocService.checkDocNameUnique(libDoc))) {
            return error("新增文档'" + libDoc.getDocName() + "'失败，文档名称已存在");
        }
        String filePath = Global.getUploadPath();
        final String tags = docModel.getTags();
        // 上传并返回新文件名称
        try {
            String doc = FileUploadUtils.upload(filePath, docModel.getDocUrl());
            libDoc.setDocUrl(serverConfig.getUrl() + File.separator + doc);
            if (null != docModel.getDocImg()) {
                String docImg = FileUploadUtils.upload(filePath, docModel.getDocImg());
                libDoc.setDocImg(serverConfig.getUrl() + File.separator + docImg);
            } else {
                final String docImg = PdfHelper.pdfToImagePath(doc.replace("/profile/upload", ""));
                libDoc.setDocImg(serverConfig.getUrl() + File.separator + "profile/upload" + docImg);
            }

        } catch (IOException e) {
            return AjaxResult.error(e.getMessage());
        }
        ShiroUtils.clearCachedAuthorizationInfo();
        final int rows = libDocService.insertDoc(libDoc);
        iLibDocTagService.addTags(tags, libDoc.getDocId());
        return toAjax(rows);

    }


    /**
     * 新增保存文档
     */
    @RequiresRoles(value = {"develop", "operator"}, logical = Logical.OR)
    @Log(title = "文档管理", businessType = BusinessType.INSERT)
    @PostMapping("/addTag")
    @ResponseBody
    public AjaxResult addTag(@Validated LibTag tag) {
        //根据标签名称查询出标签
        LibTag selectTagByName = libTagService.selectTagByName(tag);
        if (null == selectTagByName) {
            //如果标签不存在，在使用提供的标签名称进行标签插入
            tag.setCreateBy(ShiroUtils.getLoginName());
            ShiroUtils.clearCachedAuthorizationInfo();
            libTagService.insertTag(tag);
            //标签插入完成之后在进行标签查询
            return AjaxResult.success("操作成功", tag);
        }
        //返回标签查询的实体
        return AjaxResult.success("操作成功", selectTagByName);
    }

    /**
     * 校验文档名称
     */
    @PostMapping("/checkDocNameUnique")
    @ResponseBody
    public String checkDocNameUnique(LibDoc doc) {
        return libDocService.checkDocNameUnique(doc);
    }


    /**
     * 修改文档
     */
    @GetMapping("/edit/{docId}")
    public String edit(@PathVariable("docId") Long docId, ModelMap mmap) {
        mmap.put("doc", libDocService.selectDocById(docId));
        final List<LibTag> libTags = libTagService.selectTagListByDocId(docId);
        mmap.put("tagList", libTags);
        return PREFIX + "/edit";
    }

    /**
     * 修改保存文档
     */
    @RequiresRoles(value = {"develop", "operator"}, logical = Logical.OR)
    @Log(title = "文档管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    @Transactional
    public AjaxResult editSave(@Validated LibDocModel docModel) throws IOException {
        final LibDoc beforeDoc = libDocService.selectDocById(docModel.getDocId());
        LibDoc doc = generateLibDocByModel(docModel);
        // 当文档名称发生改变的时候，校验文档名称的唯一性
        if (!beforeDoc.getDocName().equals(docModel.getDocName()) && UserConstants.ROLE_NAME_NOT_UNIQUE.equals(libDocService.checkDocNameUnique(doc))) {
            return error("修改文档'" + docModel.getDocName() + "'失败，文档名称已存在");
        }
        if (null != docModel.getDocImg()) {
            String filePath = Global.getUploadPath();
            String docImg = FileUploadUtils.upload(filePath, docModel.getDocImg());
            doc.setDocImg(serverConfig.getUrl() + File.separator + docImg);
        }
        if (libDocService.updateDoc(doc) > 0) {
            LibDocTag libDocTag = new LibDocTag();
            libDocTag.setUpdateBy(ShiroUtils.getLoginName());
            libDocTag.setDocId(docModel.getDocId());
            final List<LibDocTag> libDocTags = iLibDocTagService.selectByDocId(doc.getDocId());
            iLibDocTagService.updateDocTags(libDocTags, docModel.getTags(), doc.getDocId(), ShiroUtils.getLoginName());
            ShiroUtils.clearCachedAuthorizationInfo();
        }
        return AjaxResult.success();
    }

    /**
     * 文档状态修改
     */
    @Log(title = "文档管理", businessType = BusinessType.UPDATE)
    @RequiresRoles(value = {"develop", "operator"}, logical = Logical.OR)
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(LibDoc doc) {
        return toAjax(libDocService.changeStatus(doc));
    }

    @RequiresRoles(value = {"develop", "operator"}, logical = Logical.OR)
    @Log(title = "文档管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(libDocService.deleteDocByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 获取推荐标签
     */
    @GetMapping("/tagModel")
    @ResponseBody
    public AjaxResult tagModel() {
        AjaxResult ajax = new AjaxResult();
        final LibTag query = new LibTag();
        query.setTagStatus("0");
        final List<LibTag> libTags = libTagService.selectTagList(query);
        List<TagFormModel> tagModes = new LinkedList<>();
        libTags.forEach(tag -> {
            tagModes.add(new TagFormModel(tag.getTagId(), tag.getTagName()));
        });
        ajax.put("code", 200);
        ajax.put("value", tagModes);
        return ajax;
    }

//    ********************************私有方法***************************************

    /**
     * 根据模型生成实体
     *
     * @param docModel 文档对象展示模型
     * @return 文档对象实体
     */
    private LibDoc generateLibDocByModel(@Validated LibDocModel docModel) {
        LibDoc libDoc = new LibDoc();
        libDoc.setDocId(docModel.getDocId());
        libDoc.setDocName(docModel.getDocName());
        libDoc.setCreateBy(ShiroUtils.getLoginName());
        libDoc.setDelFlag(docModel.getDelFlag());
        libDoc.setDocSort(docModel.getDocSort());
        libDoc.setDocType(docModel.getDocType());
        return libDoc;
    }

}

@Data
class TagFormModel {
    private Long tagId;
    private String tagName;

    public TagFormModel(Long tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }
}
