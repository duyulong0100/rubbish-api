package com.xserver.modules.example;

import com.xserver.dao.ExampleRepository;
import com.xserver.dao.entity.Example;
import com.xserver.common.Response;
import com.xserver.util.jpa.Op;
import com.xserver.util.jpa.SearchVo;
import com.xserver.util.jpa.SpecUtils;
import com.xserver.vo.ExampleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExampleService {
    @Autowired
    private ExampleRepository exampleRepository;

    public ExampleVo findVoById(Long id) {
        Example example = exampleRepository.findOne(id);
        return ExampleVo.covertDbtoVo(example);
    }

    public Response<List<ExampleVo>> findByPage(int pageNo, int pageSize) {
        List<SearchVo> searchParams = new ArrayList<SearchVo>();
        searchParams.add(new SearchVo("status", Op.EQ, 1));
        Sort sort = new Sort(Sort.Direction.DESC, "status");
        Specification<Example> specification = SpecUtils.buildSearchParams(Example.class, searchParams);
        Pageable pageable = SpecUtils.buildPageRequest(pageNo, pageSize, sort);
        Page<Example> pages = exampleRepository.findAll(specification, pageable);
        List<ExampleVo> content = new ArrayList<>();
        for (Example e : pages.getContent()) {
            content.add(ExampleVo.covertDbtoVo(e));
        }
        Response<List<ExampleVo>> response = new Response<>();
        response.setStatus(1);
        response.setData(content);
        response.extraField("pageNo", pageNo).extraField("pageSize", pageSize)
                .extraField("totalCount", pages.getTotalElements()).extraField("totalPage", pages.getTotalPages());
        return response;
    }
}
