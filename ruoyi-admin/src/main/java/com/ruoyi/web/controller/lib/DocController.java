package com.ruoyi.web.controller.lib;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.LibDoc;
import com.ruoyi.system.domain.LibTag;
import com.ruoyi.system.service.ILibDocService;
import com.ruoyi.system.service.ILibTagService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    private static String PREFIX = "/lib/doc";
    private final ILibDocService libDocService;
    private final ILibTagService libTagService;

    @Autowired
    public DocController(ILibDocService libDocService, ILibTagService libTagService) {
        this.libDocService = libDocService;
        this.libTagService = libTagService;
    }

    @RequiresPermissions("lib:doc:view")
    @GetMapping()
    public String doc() {
        return PREFIX + "/doc";
    }

    @RequiresPermissions("lib:doc:list")
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
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        final ArrayList<Map<String, java.io.Serializable>> objects = new ArrayList<>(2);
        mmap.put("tagList", objects);
        return PREFIX + "/add";
    }

    /**
     * 新增保存文档
     */
    @RequiresPermissions("lib:doc:add")
    @Log(title = "文档管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated LibDoc doc) {
        if (UserConstants.ROLE_NAME_NOT_UNIQUE.equals(libDocService.checkDocNameUnique(doc))) {
            return error("新增文档'" + doc.getDocName() + "'失败，文档名称已存在");
        }
        doc.setCreateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(libDocService.insertDoc(doc));

    }

    /**
     * 新增保存文档
     */
    @RequiresPermissions("lib:tag:add")
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
    @RequiresPermissions("lib:doc:edit")
    @Log(title = "文档管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated LibDoc doc) {
        if (UserConstants.ROLE_NAME_NOT_UNIQUE.equals(libDocService.checkDocNameUnique(doc))) {
            return error("修改文档'" + doc.getDocName() + "'失败，文档名称已存在");
        }
        doc.setUpdateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(libDocService.updateDoc(doc));
    }

    /**
     * 文档状态修改
     */
    @Log(title = "文档管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("lib:doc:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(LibDoc doc) {
        return toAjax(libDocService.changeStatus(doc));
    }

    @RequiresPermissions("system:role:remove")
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

}
