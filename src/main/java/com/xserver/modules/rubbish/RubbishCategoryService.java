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
import com.xserver.vo.RubbishCategoryVo;
import com.xserver.vo.ValueVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.xserver.common.ErrorCodeConstant.EC_RUBBISH_CATEGORY_BIND_ITEM_ERROR;
import static com.xserver.common.ErrorCodeConstant.EC_RUBBISH_CATEGORY_NAME_REPEAT_ERROR;

@Service
public class RubbishCategoryService extends BaseService {

    @Autowired
    private RubbishCategoryRepository rubbishCategoryRepository;

    @Autowired
    private RubbishItemRepository rubbishItemRepository;

    public Response<RubbishCategoryVo> getRubbishCategory(Long id) {
        Response<RubbishCategoryVo> response = new Response<>();
        RubbishCategory category = rubbishCategoryRepository.findOne(id);
        if (category != null) {
            RubbishCategoryVo vo = getRubbishCategoryVo(category);
            response.setData(vo);
        }
        return response;
    }

    public Response<ValueVo<Boolean>> createRubbishCategory(String categoryName, String description) {
        Response<ValueVo<Boolean>> response = new Response<>();
        response.setData(new ValueVo(false));
        List<SearchVo> searchParams = new ArrayList<>();
        searchParams.add(new SearchVo("categoryName", Op.EQ, categoryName));
        List<RubbishCategory> list = rubbishCategoryRepository.findAll(SpecUtils.buildSearchParams(
                RubbishCategory.class, searchParams));
        if (!CollectionUtils.isEmpty(list)) {
            return error(EC_RUBBISH_CATEGORY_NAME_REPEAT_ERROR);
        }

        RubbishCategory category = new RubbishCategory();
        category.setCategoryName(categoryName);
        category.setDescription(description);
        category.setStatus(1);
        category = rubbishCategoryRepository.save(category);
        if (category != null && category.getId() != null) {
            response.setData(new ValueVo(true));
        }
        return response;
    }

    @Transactional
    public Response<ValueVo<Boolean>> modifyRubbishCategory(Long id, String categoryName, String description) {
        Response<ValueVo<Boolean>> response = new Response<>();
        response.setData(new ValueVo(false));
        List<SearchVo> searchParams = new ArrayList<>();
        searchParams.add(new SearchVo("categoryName", Op.EQ, categoryName));
        List<RubbishCategory> list = rubbishCategoryRepository.findAll(SpecUtils.buildSearchParams(
                RubbishCategory.class, searchParams));
        if (!CollectionUtils.isEmpty(list)) {
            return error(EC_RUBBISH_CATEGORY_NAME_REPEAT_ERROR);
        }
        RubbishCategory category = rubbishCategoryRepository.findOne(id);
        if (category != null) {
            category.setCategoryName(categoryName);
            if (!StringUtils.isBlank(description)) {
                category.setDescription(description);
            }
            category = rubbishCategoryRepository.save(category);
            if (category != null && category.getId() != null) {
                response.setData(new ValueVo(true));
            }
        }
        return response;
    }

    @Transactional
    public Response<ValueVo<Boolean>> delRubbishCategory(Long id) {
        Response<ValueVo<Boolean>> response = new Response<>();
        response.setData(new ValueVo(false));
        RubbishCategory category = rubbishCategoryRepository.findOne(id);
        List<SearchVo> searchParams = new ArrayList<>();
        searchParams.add(new SearchVo("belongCategory", Op.EQ, category.getId()));
        List<RubbishItem> listI = rubbishItemRepository.findAll(SpecUtils.buildSearchParams(RubbishItem.class,
                searchParams));
        if (!CollectionUtils.isEmpty(listI)) {
            return error(EC_RUBBISH_CATEGORY_BIND_ITEM_ERROR);
        }

        // 删除
        rubbishCategoryRepository.delete(id);
        response.setData(new ValueVo(true));
        return response;
    }

    public Response<List<RubbishCategoryVo>> listRubbishCategory(String condition) {
        Response<List<RubbishCategoryVo>> response = new Response<>();
        List<SearchVo> searchParams = new ArrayList<>();
        searchParams.add(new SearchVo("status", Op.EQ, 1));
        if (!StringUtils.isBlank(condition)) {
            searchParams.add(new SearchVo("categoryName", Op.LIKE, condition));
        }

        List<RubbishCategory> list = rubbishCategoryRepository.findAll(SpecUtils.buildSearchParams(
                RubbishCategory.class, searchParams));
        List<RubbishCategoryVo> voList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (RubbishCategory rubbishCategory : list) {
                voList.add(RubbishCategoryVo.entityToVo(rubbishCategory));
            }
        }
        response.setData(voList);
        return response;
    }

    private RubbishCategoryVo getRubbishCategoryVo(RubbishCategory rubbishCategory) {
        RubbishCategoryVo vo = RubbishCategoryVo.entityToVo(rubbishCategory);
        return vo;
    }

}
