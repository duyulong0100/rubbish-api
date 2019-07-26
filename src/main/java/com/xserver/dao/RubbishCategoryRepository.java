package com.xserver.dao;

import com.xserver.dao.entity.RubbishCategory;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RubbishCategoryRepository extends PagingAndSortingRepository<RubbishCategory, Long>,
        JpaSpecificationExecutor<RubbishCategory> {

}