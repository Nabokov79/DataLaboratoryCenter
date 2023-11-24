package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.templates.models.HeaderTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface HeaderTemplateRepository extends JpaRepository<HeaderTemplate, Long> {

    @Query("select h from HeaderTemplate h where h.reportingDocumentId in :reportingDocumentIds")
    Set<HeaderTemplate> findAllByReportingDocumentIds(@Param("reportingDocumentIds") List<Long> reportingDocumentIds);

    Optional<HeaderTemplate> findByReportingDocumentId(Long reportingDocumentId);
}