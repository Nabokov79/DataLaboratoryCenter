package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.templates.models.ConclusionTemplate;

public interface ConclusionTemplateRepository extends JpaRepository<ConclusionTemplate, Long> {

    ConclusionTemplate findByObjectTypeIdAndReportingDocumentId(Long objectTypeId, Long reportingDocumentId);
}