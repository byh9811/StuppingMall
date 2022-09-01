package com.nerds.stuppingmall.service.report;

import com.nerds.stuppingmall.domain.Report;
import com.nerds.stuppingmall.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportSearchService {
	private final ReportRepository reportRepository;
    private final int SIZE_PER_PAGE = 10;

    public Page<Report> findAllReports(int curPage) {
        Pageable pageable = PageRequest.of(curPage, SIZE_PER_PAGE);
        return reportRepository.findAll(pageable);
    }
}