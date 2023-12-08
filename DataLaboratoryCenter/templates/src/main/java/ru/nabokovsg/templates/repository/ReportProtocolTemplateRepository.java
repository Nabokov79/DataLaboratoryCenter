package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.templates.models.enums.ProtocolType;
import ru.nabokovsg.templates.models.ReportProtocolTemplate;

public interface ReportProtocolTemplateRepository extends JpaRepository<ReportProtocolTemplate, Long> {

    ReportProtocolTemplate findByProtocolType(ProtocolType protocolType);
}