package com.liebsterkamerad.coding_assignment;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.StringWriter;
import java.util.List;

@RestController
public class ReportController {


    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/api/report/upload")
    public void upload(@RequestParam("file") MultipartFile file) {
        reportService.saveReportsFromCSV(file);
    }

    @GetMapping("/api/report/fetch-all")
    public ResponseEntity<byte[]> fetchAll() throws Exception {
        List<Report> reports = reportService.fetchAll();

        StringWriter writer = new StringWriter();
        StatefulBeanToCsv<Report> beanToCsv = new StatefulBeanToCsvBuilder<Report>(writer).build();
        beanToCsv.write(reports);

        byte[] csvData = writer.toString().getBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reports.csv");

        return new ResponseEntity<>(csvData, headers, HttpStatus.OK);
    }

    @GetMapping("api/report/fetch-by-code")
    public ResponseEntity<byte[]> fetchByCode(@RequestParam("code") String code) throws Exception {
        Report report = reportService.fetchByCode(code);

        StringWriter writer = new StringWriter();
        StatefulBeanToCsv<Report> beanToCsv = new StatefulBeanToCsvBuilder<Report>(writer).build();
        beanToCsv.write(report);

        byte[] csvData = writer.toString().getBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.csv");

        return new ResponseEntity<>(csvData, headers, HttpStatus.OK);
    }

    @DeleteMapping("api/report/delete-all")
    private void deleteAll() {
        reportService.deleteAll();
    }
}
