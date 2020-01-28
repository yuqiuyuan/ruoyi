package com.ruoyi.web.controller.lib;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.LibTag;
import com.ruoyi.system.service.ILibTagService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName TagController
 * @Description 标签管理控制器
 * @Author drebander
 * @Date 2020-01-25 4:00 PM
 * @Version 1.0
 **/

@Controller
@RequestMapping("/lib/tag")
public class TagController extends BaseController {

    private static String PREFIX = "/lib/tag";
    @Autowired
    private ILibTagService libTagService;

    @RequiresPermissions("lib:tag:view")
    @GetMapping()
    public String tag() {
        return PREFIX + "/tag";
    }

    @RequiresPermissions("lib:tag:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LibTag tag) {
        startPage();
        List<LibTag> list = libTagService.selectTagList(tag);
        return getDataTable(list);
    }

    /**
     * 新增标签
     */
    @GetMapping("/add")
    public String add() {
        return PREFIX + "/add";
    }

    /**
     * 新增保存标签
     */
    @RequiresPermissions("lib:tag:add")
    @Log(title = "标签管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated LibTag tag) {
        if (UserConstants.ROLE_NAME_NOT_UNIQUE.equals(libTagService.checkTagNameUnique(tag))) {
            return error("新增角色'" + tag.getTagName() + "'失败，标签名称已存在");
        } else if (UserConstants.ROLE_KEY_NOT_UNIQUE.equals(libTagService.checkTagKeyUnique(tag))) {
            return error("新增角色'" + tag.getTagName() + "'失败，标签权限已存在");
        }
        tag.setCreateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(libTagService.insertTag(tag));

    }

    /**
     * 校验标签名称
     */
    @PostMapping("/checkTagNameUnique")
    @ResponseBody
    public String checkTagNameUnique(LibTag tag) {
        return libTagService.checkTagNameUnique(tag);
    }

    /**
     * 校验标签权限
     */
    @PostMapping("/checkTagKeyUnique")
    @ResponseBody
    public String checkTagKeyUnique(LibTag tag) {
        return libTagService.checkTagKeyUnique(tag);
    }

    /**
     * 修改标签
     */
    @GetMapping("/edit/{tagId}")
    public String edit(@PathVariable("tagId") Long tagId, ModelMap mmap) {
        mmap.put("tag", libTagService.selectTagById(tagId));
        return PREFIX + "/edit";
    }

    /**
     * 修改保存标签
     */
    @RequiresPermissions("lib:tag:edit")
    @Log(title = "标签管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated LibTag tag) {
        if (UserConstants.ROLE_NAME_NOT_UNIQUE.equals(libTagService.checkTagNameUnique(tag))) {
            return error("修改标签'" + tag.getTagName() + "'失败，标签名称已存在");
        } else if (UserConstants.ROLE_KEY_NOT_UNIQUE.equals(libTagService.checkTagKeyUnique(tag))) {
            return error("修改标签'" + tag.getTagName() + "'失败，标签权限已存在");
        }
        tag.setUpdateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(libTagService.updateTag(tag));
    }

    /**
     * 标签状态修改
     */
    @Log(title = "标签管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("lib:tag:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(LibTag tag) {
        return toAjax(libTagService.changeStatus(tag));
    }

    @RequiresPermissions("system:role:remove")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(libTagService.deleteTagByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

}
