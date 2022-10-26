package com.nerds.stuppingmall.repository;

import com.nerds.stuppingmall.domain.Introduction;
import com.nerds.stuppingmall.domain.Report;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReportRepository extends MongoRepository<Report, Integer> {
    List<Report> findByReporterID(String reporter);
}
