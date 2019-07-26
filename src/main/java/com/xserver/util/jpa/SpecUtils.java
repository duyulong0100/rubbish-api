package com.xserver.util.jpa;

import com.xserver.util.JsonMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SpecUtils {
    public static final int DEFAULT_PAGE_NO = 1;// 默认当前页
    public static final int DEFAULT_PAGE_SIZE = 10;// 默认每页条数
    public static final int MAX_PAGE_SIZE = 1000;// 默认每页最大条数

    public static Pageable buildPageRequest(int pageNo, int pageSize) {
        return buildPageRequest(pageNo, pageSize, null);
    }

    public static Pageable buildPageRequest(int pageNo, int pageSize, Sort sort) {
        if (pageNo < 1) {
            pageNo = DEFAULT_PAGE_NO;
        }
        if (pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if (pageSize > MAX_PAGE_SIZE) {
            pageSize = MAX_PAGE_SIZE;
        }
        return new PageRequest(pageNo - 1, pageSize, sort);
    }

    public static Sort buildSortParams(List<OrderVo> voList) {
        if (voList == null || voList.size() == 0) {
            return null;
        }
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        for (OrderVo vo : voList) {
            orders.add(new Sort.Order(vo.getDirection(), vo.getField()));
        }
        return new Sort(orders);
    }

    public static List<OrderVo> parseSortParams(String orderParams) {
        if (orderParams == null || "".equals(orderParams)) {
            return null;
        }
        try {
            JsonMapper binder = new JsonMapper();
            return binder.toList(orderParams, OrderVo.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<SearchVo> parseSearchParams(String searchParams) {
        if (searchParams == null || "".equals(searchParams)) {
            return null;
        }
        try {
            JsonMapper binder = new JsonMapper();
            return binder.toList(searchParams, SearchVo.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> Specification<T> buildSearchParams(final Class<T> clazz, final SearchVo... filters) {
        if (filters == null) {
            return null;
        }
        return buildSearchParams(clazz, Arrays.asList(filters));
    }

    public static <T> Specification<T> buildSearchParams(final Class<T> clazz, final List<SearchVo> filters) {
        if (filters == null || filters.size() == 0) {
            return null;
        }
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                for (SearchVo filter : filters) {
                    // 转义嵌套路径, 如User的名为"xxx.name"的filedName, 转换为User.xxx.name属性
                    String[] names = filter.fieldName.split("\\.");
                    Path expression = root.get(names[0]);
                    for (int i = 1; i < names.length; i++) {
                        expression = expression.get(names[i]);
                    }
                    switch (filter.op) {
                    case EQ:
                        predicates.add(builder.equal(expression, filter.value));
                        break;
                    case NOT_EQ:
                        predicates.add(builder.notEqual(expression, filter.value));
                        break;
                    case GT:
                        predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
                        break;
                    case LT:
                        predicates.add(builder.lessThan(expression, (Comparable) filter.value));
                        break;
                    case GTE:
                        predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
                        break;
                    case LTE:
                        predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
                        break;
                    case LIKE:
                        predicates.add(builder.like(expression, "%" + filter.value + "%"));
                        break;
                    case L_LIKE:
                        predicates.add(builder.like(expression, "%" + filter.value));
                        break;
                    case R_LIKE:
                        predicates.add(builder.like(expression, filter.value + "%"));
                        break;
                    case IS_NULL:
                        predicates.add(builder.isNull(expression));
                        break;
                    case NOT_NULL:
                        predicates.add(builder.isNotNull(expression));
                        break;
                    case IN:
                        predicates.add(expression.in((Object[]) filter.value));
                        break;
                    case NOT_IN:
                        predicates.add(expression.in((Object[]) filter.value).not());
                        break;
                    }
                }
                // 将所有条件用 and 联合起来
                if (!predicates.isEmpty()) {
                    return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
                return builder.conjunction();
            }
        };
    }

    public static <T> Specification<T> buildOrSearchParams(final Class<T> clazz, final List<SearchVo> filters) {
        if (filters == null || filters.size() == 0) {
            return null;
        }
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                for (SearchVo filter : filters) {
                    // 转义嵌套路径, 如User的名为"xxx.name"的filedName, 转换为User.xxx.name属性
                    String[] names = filter.fieldName.split("\\.");
                    Path expression = root.get(names[0]);
                    for (int i = 1; i < names.length; i++) {
                        expression = expression.get(names[i]);
                    }
                    switch (filter.op) {
                    case EQ:
                        predicates.add(builder.equal(expression, filter.value));
                        break;
                    case NOT_EQ:
                        predicates.add(builder.notEqual(expression, filter.value));
                        break;
                    case GT:
                        predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
                        break;
                    case LT:
                        predicates.add(builder.lessThan(expression, (Comparable) filter.value));
                        break;
                    case GTE:
                        predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
                        break;
                    case LTE:
                        predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
                        break;
                    case LIKE:
                        predicates.add(builder.like(expression, "%" + filter.value + "%"));
                        break;
                    case L_LIKE:
                        predicates.add(builder.like(expression, "%" + filter.value));
                        break;
                    case R_LIKE:
                        predicates.add(builder.like(expression, filter.value + "%"));
                        break;
                    case IS_NULL:
                        predicates.add(builder.isNull(expression));
                        break;
                    case NOT_NULL:
                        predicates.add(builder.isNotNull(expression));
                        break;
                    case IN:
                        predicates.add(expression.in((Object[]) filter.value));
                        break;
                    case NOT_IN:
                        predicates.add(expression.in((Object[]) filter.value).not());
                        break;
                    }
                }
                // 将所有条件用 or 联合起来
                if (!predicates.isEmpty()) {
                    return builder.or(predicates.toArray(new Predicate[predicates.size()]));
                }
                return builder.conjunction();
            }
        };
    }

    // 设置时间区间
    public static void setTimeRegin(List<SearchVo> orderSearchParams, String fieldName, String timeRange) {
        if (timeRange == null) {
            return;
        }
        String[] timeRangeArray = timeRange.split(":");
        Long timeStar = Long.valueOf(timeRangeArray[0]);
        Long timeEnd = Long.valueOf(timeRangeArray[1]);

        orderSearchParams.add(new SearchVo(fieldName, Op.GTE, new Date(timeStar)));

        orderSearchParams.add(new SearchVo(fieldName, Op.LTE, new Date(timeEnd)));
    }

    public static <T> Specification<T> buildAndOrSearchParams(final Class<T> clazz, final List<SearchVo> andFilters,
            final List<SearchVo> orFilters) {
        if (andFilters == null || orFilters == null) {
            return null;
        }
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> andPredicates = new ArrayList<Predicate>();
                for (SearchVo filter : andFilters) {
                    // 转义嵌套路径, 如User的名为"xxx.name"的filedName, 转换为User.xxx.name属性
                    String[] names = filter.fieldName.split("\\.");
                    Path expression = root.get(names[0]);
                    for (int i = 1; i < names.length; i++) {
                        expression = expression.get(names[i]);
                    }
                    switch (filter.op) {
                    case EQ:
                        andPredicates.add(builder.equal(expression, filter.value));
                        break;
                    case NOT_EQ:
                        andPredicates.add(builder.notEqual(expression, filter.value));
                        break;
                    case GT:
                        andPredicates.add(builder.greaterThan(expression, (Comparable) filter.value));
                        break;
                    case LT:
                        andPredicates.add(builder.lessThan(expression, (Comparable) filter.value));
                        break;
                    case GTE:
                        andPredicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
                        break;
                    case LTE:
                        andPredicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
                        break;
                    case LIKE:
                        andPredicates.add(builder.like(expression, "%" + filter.value + "%"));
                        break;
                    case L_LIKE:
                        andPredicates.add(builder.like(expression, "%" + filter.value));
                        break;
                    case R_LIKE:
                        andPredicates.add(builder.like(expression, filter.value + "%"));
                        break;
                    case IS_NULL:
                        andPredicates.add(builder.isNull(expression));
                        break;
                    case NOT_NULL:
                        andPredicates.add(builder.isNotNull(expression));
                        break;
                    case IN:
                        andPredicates.add(expression.in((Object[]) filter.value));
                        break;
                    case NOT_IN:
                        andPredicates.add(expression.in((Object[]) filter.value).not());
                        break;
                    }
                }

                List<Predicate> orPredicates = new ArrayList<Predicate>();
                for (SearchVo filter : orFilters) {
                    // 转义嵌套路径, 如User的名为"xxx.name"的filedName, 转换为User.xxx.name属性
                    String[] names = filter.fieldName.split("\\.");
                    Path expression = root.get(names[0]);
                    for (int i = 1; i < names.length; i++) {
                        expression = expression.get(names[i]);
                    }
                    switch (filter.op) {
                    case EQ:
                        orPredicates.add(builder.equal(expression, filter.value));
                        break;
                    case NOT_EQ:
                        orPredicates.add(builder.notEqual(expression, filter.value));
                        break;
                    case GT:
                        orPredicates.add(builder.greaterThan(expression, (Comparable) filter.value));
                        break;
                    case LT:
                        orPredicates.add(builder.lessThan(expression, (Comparable) filter.value));
                        break;
                    case GTE:
                        orPredicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
                        break;
                    case LTE:
                        orPredicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
                        break;
                    case LIKE:
                        orPredicates.add(builder.like(expression, "%" + filter.value + "%"));
                        break;
                    case L_LIKE:
                        orPredicates.add(builder.like(expression, "%" + filter.value));
                        break;
                    case R_LIKE:
                        orPredicates.add(builder.like(expression, filter.value + "%"));
                        break;
                    case IS_NULL:
                        orPredicates.add(builder.isNull(expression));
                        break;
                    case NOT_NULL:
                        orPredicates.add(builder.isNotNull(expression));
                        break;
                    case IN:
                        orPredicates.add(expression.in((Object[]) filter.value));
                        break;
                    case NOT_IN:
                        orPredicates.add(expression.in((Object[]) filter.value).not());
                        break;
                    }
                }
                // 将所有条件用 and 联合起来
                Predicate predicate1 = null;
                Predicate predicate2 = null;
                if (!andPredicates.isEmpty()) {
                    predicate1 = builder.and(andPredicates.toArray(new Predicate[andPredicates.size()]));
                }
                // 将所有条件用 or 联合起来
                if (!orPredicates.isEmpty()) {
                    predicate2 = builder.or(orPredicates.toArray(new Predicate[orPredicates.size()]));
                }
                List<Predicate> predicates = new ArrayList<>();
                if (predicate1 != null) {
                    predicates.add(predicate1);
                }
                if (predicate2 != null) {
                    predicates.add(predicate2);
                }
                if (!predicates.isEmpty()) {
                    return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                }

                return builder.conjunction();
            }
        };
    }

}