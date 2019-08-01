package com.xserver.modules.rubbish;

import com.xserver.common.BaseController;
import com.xserver.common.Response;
import com.xserver.vo.RubbishItemVo;
import com.xserver.vo.ValueVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rubbish")
@Api(tags = { "垃圾信息接口" })
public class RubbishItemController extends BaseController {

    @Autowired
    private RubbishItemService rubbishItemService;

    @ApiOperation(value = "垃圾信息查询", notes = "/item/get")
    @RequestMapping(value = "/item/get", method = { RequestMethod.POST, RequestMethod.GET })
    public Response<RubbishItemVo> itemGet(@RequestParam(value = "id") Long id) {
        return rubbishItemService.getRubbishItem(id);
    }

    @ApiOperation(value = "垃圾信息创建", notes = "/item/create")
    @RequestMapping(value = "/item/create", method = { RequestMethod.POST, RequestMethod.GET })
    public Response<ValueVo<Boolean>> itemCreate(
            @ApiParam(value = "垃圾名称", required = true) @RequestParam(value = "itemName") String itemName,
            @ApiParam(value = "所属分类", required = true) @RequestParam(value = "categoryId") Long categoryId,
            @ApiParam(value = "状态") @RequestParam(value = "status", required = false, defaultValue = "1") int status) {
        return rubbishItemService.createRubbishItem(itemName, categoryId, status);
    }

    @ApiOperation(value = "垃圾信息编辑", notes = "/item/edit")
    @RequestMapping(value = "/item/edit", method = { RequestMethod.POST, RequestMethod.GET })
    public Response<ValueVo<Boolean>> itemEdit(@RequestParam(value = "id") Long id,
            @ApiParam(value = "垃圾名称", required = true) @RequestParam(value = "itemName") String itemName,
            @ApiParam(value = "所属分类", required = true) @RequestParam(value = "categoryId") Long categoryId,
            @ApiParam(value = "状态") @RequestParam(value = "status", required = false, defaultValue = "1") int status) {
        return rubbishItemService.modifyRubbishItem(id, itemName, categoryId, status);
    }

    @ApiOperation(value = "垃圾信息删除", notes = "/item/del")
    @RequestMapping(value = "/item/del", method = { RequestMethod.POST, RequestMethod.GET })
    public Response<ValueVo<Boolean>> itemDel(@RequestParam(value = "id") Long id) {
        return rubbishItemService.delRubbishItem(id);
    }

    @ApiOperation(value = "垃圾信息列表", notes = "/item/list")
    @RequestMapping(value = "/item/list", method = { RequestMethod.POST, RequestMethod.GET })
    public Response<List<RubbishItemVo>> itemList(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @ApiParam(value = "名称作条件查询") @RequestParam(value = "keyword", required = false) String keyword,
            @ApiParam(value = "所属分类") @RequestParam(value = "categoryId", required = false) Long categoryId) {
        return rubbishItemService.listRubbishItem(page, size, keyword, categoryId);
    }
}