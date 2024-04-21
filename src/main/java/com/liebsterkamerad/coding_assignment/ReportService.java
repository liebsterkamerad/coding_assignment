package com.liebsterkamerad.coding_assignment;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> fetchAll() {
        return reportRepository.findAll();
    }

    public List<Report> readReportsFromCSV(MultipartFile file) {
        try (Reader reader = new InputStreamReader(file.getInputStream())) {
            CsvToBean<Report> csvToBean = new CsvToBeanBuilder<Report>(reader)
                    .withType(Report.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }

    public void saveReportsFromCSV(MultipartFile file) {
        List<Report> reports = readReportsFromCSV(file);
        reportRepository.saveAll(reports);
    }

    public Report fetchByCode(String code) {
        return reportRepository.findFirstByCode(code);
    }

    public void deleteAll() {
        reportRepository.deleteAll();
    }
}
