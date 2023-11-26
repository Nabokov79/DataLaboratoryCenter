package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.pageTitle.ShortPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.report.ReportTemplateDto;
import ru.nabokovsg.templates.models.ReportTemplate;

import java.util.List;

public interface ReportTemplateService {

    ReportTemplateDto create(Long reportingDocumentId, Long objectsTypeId);

    ReportTemplateDto save(ReportTemplate report);

    ReportTemplateDto get(Long objectTypeId, Long reportingDocumentId);

    List<ShortPageTitleTemplateDto> getAll();
}