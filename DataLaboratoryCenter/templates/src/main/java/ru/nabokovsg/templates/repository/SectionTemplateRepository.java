package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.templates.models.SectionTemplate;

import java.util.Set;

public interface SectionTemplateRepository extends JpaRepository<SectionTemplate, Long> {

    Set<SectionTemplate> findAllByReportingDocumentId(Long reportingDocumentId);
}