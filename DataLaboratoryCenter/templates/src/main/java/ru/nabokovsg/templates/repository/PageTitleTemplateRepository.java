package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.templates.models.PageTitleTemplate;

import java.util.Optional;

public interface PageTitleTemplateRepository extends JpaRepository<PageTitleTemplate, Long> {

   Optional<PageTitleTemplate> findByReportingDocumentId(Long reportingDocumentId);

    boolean existsByReportingDocumentId(Long reportingDocumentId);
}