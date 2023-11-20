package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.templates.models.ProtocolTemplate;
import ru.nabokovsg.templates.models.Title;

import java.util.List;

public interface ProtocolTemplateRepository extends JpaRepository<ProtocolTemplate, Long> {

   ProtocolTemplate findByObjectTypeIdAndReportingDocumentId(Long objectsTypeId, Long reportingDocumentId);

   @Query("select p from Title p")
   List<Title> findAllTitle();
}