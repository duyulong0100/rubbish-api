package com.xserver.dao;

import com.xserver.dao.entity.RubbishItem;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RubbishItemRepository extends PagingAndSortingRepository<RubbishItem, Long>,
        JpaSpecificationExecutor<RubbishItem> {

}