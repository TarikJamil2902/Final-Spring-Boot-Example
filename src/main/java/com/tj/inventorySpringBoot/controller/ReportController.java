package com.tj.inventorySpringBoot.controller;

import com.tj.inventorySpringBoot.dto.ReportDTO;
import com.tj.inventorySpringBoot.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Endpoint to create or update a report
    @PostMapping
    public ResponseEntity<ReportDTO> createOrUpdateReport(@RequestBody ReportDTO reportDTO) {
        ReportDTO savedReport = reportService.saveReport(reportDTO);
        return new ResponseEntity<>(savedReport, HttpStatus.CREATED);
    }

    // Endpoint to get all reports
    @GetMapping
    public ResponseEntity<List<ReportDTO>> getAllReports() {
        List<ReportDTO> reports = reportService.getAllReports();
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    // Endpoint to get a report by its ID
    @GetMapping("/{id}")
    public ResponseEntity<ReportDTO> getReportById(@PathVariable Long id) {
        ReportDTO reportDTO = reportService.getReportById(id);
        if (reportDTO != null) {
            return new ResponseEntity<>(reportDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Report not found
    }

    // Endpoint to delete a report by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Successfully deleted
    }
}

