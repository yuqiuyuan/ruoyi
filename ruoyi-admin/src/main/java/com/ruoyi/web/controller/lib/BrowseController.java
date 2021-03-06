package com.ruoyi.web.controller.lib;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.LibDoc;
import com.ruoyi.system.domain.LibTag;
import com.ruoyi.system.service.ILibDocService;
import com.ruoyi.system.service.ILibTagService;
import org.apache.shiro.authz.annotation.RequiresGuest;
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
@RequestMapping("/lib/")
public class BrowseController extends BaseController {

    private static String PREFIX = "lib/browse";
    private final ILibTagService libTagService;
    private final ILibDocService libDocService;

    @Autowired
    public BrowseController(ILibTagService libTagService, ILibDocService libDocService) {
        this.libTagService = libTagService;
        this.libDocService = libDocService;
    }

    @RequiresGuest
    @GetMapping("browse")
    public String browse(ModelMap mmap) {
        final LibTag query = new LibTag();
        query.setTagStatus("0");
        final List<LibTag> libTags = libTagService.selectTagList(query);
        mmap.put("tagList", libTags);
        final List<LibDoc> libDocs = libDocService.selectDocList(new LibDoc());
        mmap.put("docList", libDocs);
        return PREFIX + "/browseH5";
    }

    @RequiresGuest
    @PostMapping("browse/list")
    @ResponseBody
    public TableDataInfo list(LibTag tag) {
        startPage();
        List<LibTag> list = libTagService.selectTagList(tag);
        return getDataTable(list);
    }

    @RequiresGuest
    @GetMapping("browse_web")
    public String doc(ModelMap mmap) {
        final List<LibTag> libTags = libTagService.selectTagList(new LibTag());
        mmap.put("tagList", libTags);
        return PREFIX + "/browsWeb";
    }

}
