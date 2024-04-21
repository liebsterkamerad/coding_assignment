package com.liebsterkamerad.coding_assignment;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController {


    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/api/report/upload")
    public void upload() {}

    @GetMapping("/api/report/fetch-all")
    public List<Report> fetchAll() {
        return reportService.fetchAll();
    }

    @GetMapping("api/report/fetch-by-code")
    private void fetchByCode() {}

    @DeleteMapping("api/report/delete")
    private void deleteAll() {

    }
}
