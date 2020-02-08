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
 * 标签管理控制器
 *
 * @author drebander
 * @since 2020-01-28
 */

@Controller
@RequestMapping("/lib/browse")
public class BrowseController extends BaseController {

    private static String PREFIX = "/lib/browse";
    private final ILibTagService libTagService;

    @Autowired
    public BrowseController(ILibTagService libTagService) {
        this.libTagService = libTagService;
    }

    @RequiresPermissions("lib:browse:view")
    @GetMapping()
    public String browse() {
        return PREFIX + "/browseH5";
    }

    @RequiresPermissions("lib:tag:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LibTag tag) {
        startPage();
        List<LibTag> list = libTagService.selectTagList(tag);
        return getDataTable(list);
    }

}
