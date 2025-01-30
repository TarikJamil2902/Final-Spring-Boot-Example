package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.ReportDTO;
import com.tj.inventorySpringBoot.entity.Report;
import com.tj.inventorySpringBoot.entity.User;
import com.tj.inventorySpringBoot.repository.ReportRepository;
import com.tj.inventorySpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new report
    public ReportDTO createReport(ReportDTO reportDTO) {
        Report report = convertToEntity(reportDTO);
        Report savedReport = reportRepository.save(report);
        return convertToDTO(savedReport);
    }

    // Update an existing report
    public ReportDTO updateReport(Long id, ReportDTO reportDTO) {
        Optional<Report> existingReportOptional = reportRepository.findById(id);
        if (existingReportOptional.isPresent()) {
            Report existingReport = existingReportOptional.get();

            // Update fields
            existingReport.setTitle(reportDTO.getTitle());
            existingReport.setContent(reportDTO.getContent());

            // Update the createdBy user
            User createdByUser = userRepository.findByUserName(reportDTO.getCreatedByUserName()).get();
            existingReport.setCreatedBy(createdByUser);

            Report updatedReport = reportRepository.save(existingReport);
            return convertToDTO(updatedReport);
        }
        return null; // Return null or throw an exception if not found
    }

    // Get all reports
    public List<ReportDTO> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a report by its ID
    public ReportDTO getReportById(Long id) {
        Optional<Report> reportOptional = reportRepository.findById(id);
        if (reportOptional.isPresent()) {
            return convertToDTO(reportOptional.get());
        }
        return null; // Return null or throw an exception if not found
    }

    // Delete a report by its ID
    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }

    // Convert ReportDTO to Report entity
    private Report convertToEntity(ReportDTO reportDTO) {
        Report report = new Report();
        report.setId(reportDTO.getId());
        report.setTitle(reportDTO.getTitle());
        report.setContent(reportDTO.getContent());

        // Set the createdBy user from the User repository based on userName
        User createdByUser = userRepository.findByUserName(reportDTO.getCreatedByUserName()).get();
        report.setCreatedBy(createdByUser);

        return report;
    }

    // Convert Report entity to ReportDTO
    private ReportDTO convertToDTO(Report report) {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setId(report.getId());
        reportDTO.setTitle(report.getTitle());
        reportDTO.setContent(report.getContent());

        // Set createdByUserName from the createdBy user
        if (report.getCreatedBy() != null) {
            reportDTO.setCreatedByUserName(report.getCreatedBy().getUserName());
        }

        return reportDTO;
    }
}
