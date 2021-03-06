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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        searchParams.add(new SearchVo("itemName", Op.EQ, itemName));
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

    public Response<List<RubbishItemVo>> listRubbishItem(int page, int size, String keyword, Long categoryId) {
        Response<List<RubbishItemVo>> response = new Response<>();
        Pageable pageable = new PageRequest((page - 1) >= 0 ? (page - 1) : 0, size, Sort.Direction.ASC, "id");
        List<SearchVo> searchParams = new ArrayList<>();
        searchParams.add(new SearchVo("status", Op.IN, new Object[] { 0, 1 }));
        if (!StringUtils.isBlank(keyword)) {
            searchParams.add(new SearchVo("itemName", Op.LIKE, keyword));
        }
        if (categoryId != null && categoryId != 0) {
            searchParams.add(new SearchVo("belongCategory", Op.EQ, categoryId));
        }
        Page<RubbishItem> pageRI = rubbishItemRepository.findAll(
                SpecUtils.buildSearchParams(RubbishItem.class, searchParams), pageable);
        List<RubbishItemVo> voList = new ArrayList<>();
        if (pageRI != null && !CollectionUtils.isEmpty(pageRI.getContent())) {
            for (RubbishItem rubbishItem : pageRI.getContent()) {
                voList.add(getRubbishItemVo(rubbishItem));
            }
        }
        response.setData(voList);
        response.extraField("pageNo", page).extraField("pageSize", size)
                .extraField("totalCount", pageRI.getTotalElements()).extraField("totalPage", pageRI.getTotalPages());
        return response;
    }

    private RubbishItemVo getRubbishItemVo(RubbishItem rubbishItem) {
        RubbishItemVo vo = RubbishItemVo.entityToVo(rubbishItem);
        return vo;
    }

}
