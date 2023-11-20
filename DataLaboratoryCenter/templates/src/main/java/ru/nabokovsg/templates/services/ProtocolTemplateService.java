package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.templates.client.dto.ReportingDocumentDto;
import ru.nabokovsg.templates.dto.title.TitleDto;
import ru.nabokovsg.templates.models.HeaderTemplate;

import java.util.List;

public interface ProtocolTemplateService {

    void create(HeaderTemplate header, ReportingDocumentDto reportingDocument, Long objectTypeId);

    ProtocolTemplateDto get(Long objectTypeId, Long reportingDocumentId);

    List<TitleDto> getAll();
}