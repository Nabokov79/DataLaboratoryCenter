package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.templates.models.ReportTemplate;

public interface ReportTemplateRepository extends JpaRepository<ReportTemplate, Long> {

    @Query("select r from ReportTemplate r where r.pageTitle.id = ?1")
    ReportTemplate findByPageTitleId(Long id);

    ReportTemplate findByObjectTypeIdAndReportingDocumentId(Long objectTypeId, Long reportingDocumentId);
}