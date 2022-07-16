package com.nerds.stuppingmall.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public final class SearchParameterInitializer {
    private final int DEFAULT_PAGE_SIZE = 10;

    public Sort getSortMethod(String sortingOrder) {
        Sort sort;

        switch(sortingOrder) {
            case "최신순": sort = Sort.by(Sort.Direction.DESC, "registerDate"); break;
            case "인기순": sort = Sort.by(Sort.Direction.DESC, "rate"); break;
            case "판매순": sort = Sort.by(Sort.Direction.DESC, "salesVolume"); break;
            case "조회순": sort = Sort.by(Sort.Direction.DESC, "view"); break;
            case "낮은가격순": sort = Sort.by(Sort.Direction.ASC, "price"); break;
            case "높은가격순": sort = Sort.by(Sort.Direction.DESC, "price"); break;
            default: throw new RuntimeException();
        }

        return sort;
    }

    public Pageable getPageable(int curPage) {
        return PageRequest.of(curPage, DEFAULT_PAGE_SIZE);
    }
    public Pageable getPageable(int curPage, int pageSize) {
        return PageRequest.of(curPage, pageSize);
    }
    public Pageable getPageable(int curPage, String sortingOrder) { return PageRequest.of(curPage, DEFAULT_PAGE_SIZE, getSortMethod(sortingOrder)); }
    public Pageable getPageable(int curPage, int pageSize, String sortingOrder) { return PageRequest.of(curPage, pageSize, getSortMethod(sortingOrder)); }
}
