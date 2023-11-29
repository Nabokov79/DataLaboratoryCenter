package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.templates.models.PageTitleTemplate;
import java.util.Set;

public interface PageTitleTemplateRepository extends JpaRepository<PageTitleTemplate, Long> {

    boolean existsByReportingDocumentIdAndObjectTypeId(Long reportingDocumentId, Long objectTypeId);

    Set<PageTitleTemplate> findAllByReportingDocumentId(Long reportingDocumentId);

    PageTitleTemplate findByObjectTypeIdAndReportingDocumentId(Long objectTypeId, Long reportingDocumentId);
}