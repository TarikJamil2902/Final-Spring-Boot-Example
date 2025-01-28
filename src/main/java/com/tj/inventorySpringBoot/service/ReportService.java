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
    private UserRepository userRepository; // Assuming you have a User repository to fetch the user who created the report

    // Create or update a report
    public ReportDTO saveReport(ReportDTO reportDTO) {
        Report report = convertToEntity(reportDTO);
        Report savedReport = reportRepository.save(report);
        return convertToDTO(savedReport);
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
        return null; // You can throw an exception or return 404 in the controller
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

        // Set the createdBy user from the User repository
        User createdByUser = userRepository.findById(reportDTO.getCreatedByUserId()).orElse(null);
        report.setCreatedBy(createdByUser);

        return report;
    }

    // Convert Report entity to ReportDTO
    private ReportDTO convertToDTO(Report report) {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setId(report.getId());
        reportDTO.setTitle(report.getTitle());
        reportDTO.setContent(report.getContent());

        // Set createdByUserId from the createdBy user
        if (report.getCreatedBy() != null) {
            reportDTO.setCreatedByUserId(report.getCreatedBy().getId());
        }

        return reportDTO;
    }
}

