package com.ruoyi.web.controller.lib;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.LibDoc;
import com.ruoyi.system.domain.LibTag;
import com.ruoyi.system.service.ILibDocService;
import com.ruoyi.system.service.ILibTagService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

    private static String PREFIX = "lib/browse";
    private final ILibTagService libTagService;
    private final ILibDocService libDocService;

    @Autowired
    public BrowseController(ILibTagService libTagService, ILibDocService libDocService) {
        this.libTagService = libTagService;
        this.libDocService = libDocService;
    }

    @RequiresPermissions("lib:browse:view")
    @GetMapping()
    public String browse(ModelMap mmap) {
        final LibTag query = new LibTag();
        query.setTagStatus("0");
        final List<LibTag> libTags = libTagService.selectTagList(query);
        mmap.put("tagList", libTags);
        final List<LibDoc> libDocs = libDocService.selectDocList(new LibDoc());
        mmap.put("docList", libDocs);
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
