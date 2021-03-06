package com.xserver.modules.rubbish;

import com.xserver.common.BaseController;
import com.xserver.common.Response;
import com.xserver.vo.RubbishCategoryVo;
import com.xserver.vo.ValueVo;
import com.xserver.vo.request.SearchReqVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rubbish")
@Api(tags = { "垃圾分类信息接口" })
public class RubbishCategoryController extends BaseController {

    @Autowired
    private RubbishCategoryService rubbishCategoryService;

    @ApiOperation(value = "分类查询", notes = "/category/get")
    @RequestMapping(value = "/category/get", method = { RequestMethod.POST, RequestMethod.GET })
    public Response<RubbishCategoryVo> itemGet(@RequestParam(value = "id") Long id) {
        return rubbishCategoryService.getRubbishCategory(id);
    }

    @ApiOperation(value = "分类创建", notes = "/category/create")
    @RequestMapping(value = "/category/create", method = { RequestMethod.POST, RequestMethod.GET })
    public Response<ValueVo<Boolean>> categoryCreate(
            @ApiParam(value = "分类名称", required = true) @RequestParam(value = "categoryName") String categoryName,
            @ApiParam(value = "分类描述") @RequestParam(value = "description", required = false) String description,
            @ApiParam(value = "分类图") @RequestParam(value = "picUrl", required = false) String picUrl,
            @ApiParam(value = "状态") @RequestParam(value = "status", required = false, defaultValue = "1") int status) {
        return rubbishCategoryService.createRubbishCategory(categoryName, description, picUrl, status);
    }

    @ApiOperation(value = "分类编辑", notes = "/category/edit")
    @RequestMapping(value = "/category/edit", method = { RequestMethod.POST, RequestMethod.GET })
    public Response<ValueVo<Boolean>> categoryEdit(@RequestParam(value = "id") Long id,
            @ApiParam(value = "分类名称", required = true) @RequestParam(value = "categoryName") String categoryName,
            @ApiParam(value = "分类描述") @RequestParam(value = "description", required = false) String description,
            @ApiParam(value = "分类图") @RequestParam(value = "picUrl", required = false) String picUrl,
            @ApiParam(value = "状态") @RequestParam(value = "status", required = false, defaultValue = "1") int status) {
        return rubbishCategoryService.modifyRubbishCategory(id, categoryName, description, picUrl, status);
    }

    @ApiOperation(value = "分类删除", notes = "/category/del")
    @RequestMapping(value = "/category/del", method = { RequestMethod.POST, RequestMethod.GET })
    public Response<ValueVo<Boolean>> categoryDel(@RequestParam(value = "id") Long id) {
        return rubbishCategoryService.delRubbishCategory(id);
    }

    @ApiOperation(value = "分类列表", notes = "/category/list")
    @RequestMapping(value = "/category/list", method = { RequestMethod.GET })
    public Response<List<RubbishCategoryVo>> categoryListGet(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @ApiParam(value = "名称作条件查询") @RequestParam(value = "keyword", required = false) String keyword) {
        return rubbishCategoryService.listRubbishCategory(page, size, keyword);
    }

    @ApiOperation(value = "分类列表", notes = "/category/list")
    @RequestMapping(value = "/category/list", method = { RequestMethod.POST })
    public Response<List<RubbishCategoryVo>> categoryListPost(
            @ApiParam(value = "查询条件") @RequestBody SearchReqVo searchReqVo) {
        return rubbishCategoryService.listRubbishCategory(searchReqVo.getPage(), searchReqVo.getSize(),
                searchReqVo.getKeyword());
    }
}