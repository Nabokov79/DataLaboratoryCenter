package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.report.ReportTemplateDto;
import ru.nabokovsg.templates.models.PageTitleTemplate;
import ru.nabokovsg.templates.models.ReportTemplate;

public interface ReportTemplateService {

    void create(PageTitleTemplate pageTitle, Long reportingDocumentId, Long objectsTypeId);

    ReportTemplateDto save(ReportTemplate report);

    ReportTemplateDto get(Long objectsTypeId, Long reportingDocumentId);

    ReportTemplate getById(Long id);

    ReportTemplate getByPageTitle(Long pageTitleId);
}