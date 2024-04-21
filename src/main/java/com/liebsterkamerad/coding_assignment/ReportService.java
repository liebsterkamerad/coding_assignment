package com.liebsterkamerad.coding_assignment;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ReportService {

    private ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> fetchAll() {
        return reportRepository;
    }
}
