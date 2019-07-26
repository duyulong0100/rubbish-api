package com.xserver.modules.rubbish;

import com.xserver.common.BaseService;
import com.xserver.common.Response;
import com.xserver.dao.RubbishCategoryRepository;
import com.xserver.dao.RubbishItemRepository;
import com.xserver.dao.entity.RubbishCategory;
import com.xserver.dao.entity.RubbishItem;
import com.xserver.util.jpa.Op;
import com.xserver.util.jpa.SearchVo;
import com.xserver.util.jpa.SpecUtils;
import com.xserver.vo.RubbishItemVo;
import com.xserver.vo.ValueVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.xserver.common.ErrorCodeConstant.EC_RUBBISH_ITEM_NAME_REPEAT_ERROR;

@Service
public class RubbishItemService extends BaseService {

    @Autowired
    private RubbishItemRepository rubbishItemRepository;

    @Autowired
    private RubbishCategoryRepository rubbishCategoryRepository;

    public Response<RubbishItemVo> getRubbishItem(Long id) {
        Response<RubbishItemVo> response = new Response<>();
        RubbishItem item = rubbishItemRepository.findOne(id);
        if (item != null) {
            RubbishItemVo vo = getRubbishItemVo(item);
            response.setData(vo);
        }
        return response;
    }

    public Response<ValueVo<Boolean>> createRubbishItem(String itemName, Long categoryId, int status) {
        Response<ValueVo<Boolean>> response = new Response<>();
        response.setData(new ValueVo(false));
        List<SearchVo> searchParams = new ArrayList<>();
        searchParams.add(new SearchVo("itemName", Op.EQ, itemName));
        List<RubbishItem> list = rubbishItemRepository.findAll(SpecUtils.buildSearchParams(RubbishItem.class,
                searchParams));
        if (!CollectionUtils.isEmpty(list)) {
            return error(EC_RUBBISH_ITEM_NAME_REPEAT_ERROR);
        }

        RubbishItem item = new RubbishItem();
        item.setItemName(itemName);
        item.setCreateTime(new Date());
        item.setStatus(status);
        RubbishCategory category = null;
        if (categoryId != null && categoryId != 0) {
            category = rubbishCategoryRepository.findOne(categoryId);
        }
        if (category != null && category.getId() != null) {
            item.setBelongCategory(category);
        }
        item = rubbishItemRepository.save(item);
        if (item != null && item.getId() != null) {
            response.setData(new ValueVo(true));
        }
        return response;
    }

    @Transactional
    public Response<ValueVo<Boolean>> modifyRubbishItem(Long id, String itemName, Long categoryId, int status) {
        Response<ValueVo<Boolean>> response = new Response<>();
        response.setData(new ValueVo(false));
        List<SearchVo> searchParams = new ArrayList<>();
        searchParams.add(new SearchVo("categoryName", Op.EQ, itemName));
        List<RubbishItem> list = rubbishItemRepository.findAll(SpecUtils.buildSearchParams(RubbishItem.class,
                searchParams));
        boolean isRepeat = false;
        if (!CollectionUtils.isEmpty(list)) {
            for (RubbishItem item : list) {
                if (item.getId().compareTo(id) != 0) {
                    isRepeat = true;
                    break;
                }
            }
        }
        if (isRepeat) {
            return error(EC_RUBBISH_ITEM_NAME_REPEAT_ERROR);
        }
        RubbishItem item = rubbishItemRepository.findOne(id);
        if (item != null) {
            item.setItemName(itemName);
            item.setStatus(status);
            RubbishCategory category = null;
            if (categoryId != null && categoryId != 0) {
                category = rubbishCategoryRepository.findOne(categoryId);
            }
            if (category != null && category.getId() != null) {
                item.setBelongCategory(category);
            }
            item = rubbishItemRepository.save(item);
            if (item != null && item.getId() != null) {
                response.setData(new ValueVo(true));
            }
        }
        return response;
    }

    @Transactional
    public Response<ValueVo<Boolean>> delRubbishItem(Long id) {
        Response<ValueVo<Boolean>> response = new Response<>();
        response.setData(new ValueVo(false));

        // 删除
        rubbishItemRepository.delete(id);
        RubbishItem item = rubbishItemRepository.findOne(id);
        if (item == null) {
            response.setData(new ValueVo(true));
        }
        return response;
    }

    public Response<List<RubbishItemVo>> listRubbishItem(String condition, Long categoryId) {
        Response<List<RubbishItemVo>> response = new Response<>();
        List<SearchVo> searchParams = new ArrayList<>();
        searchParams.add(new SearchVo("status", Op.IN, new Object[] { 0, 1 }));
        if (!StringUtils.isBlank(condition)) {
            searchParams.add(new SearchVo("itemCategory", Op.LIKE, condition));
        }
        if (categoryId != null && categoryId != 0) {
            searchParams.add(new SearchVo("belongCategory", Op.EQ, categoryId));
        }
        List<RubbishItem> list = rubbishItemRepository.findAll(SpecUtils.buildSearchParams(RubbishItem.class,
                searchParams));
        List<RubbishItemVo> voList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (RubbishItem rubbishItem : list) {
                voList.add(getRubbishItemVo(rubbishItem));
            }
        }
        response.setData(voList);
        return response;
    }

    private RubbishItemVo getRubbishItemVo(RubbishItem rubbishItem) {
        RubbishItemVo vo = RubbishItemVo.entityToVo(rubbishItem);
        return vo;
    }

}
