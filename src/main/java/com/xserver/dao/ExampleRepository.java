package com.xserver.dao;

import com.xserver.dao.entity.Example;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExampleRepository extends PagingAndSortingRepository<Example, Long>, JpaSpecificationExecutor<Example> {

}