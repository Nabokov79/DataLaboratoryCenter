package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.report.ReportTemplateDto;
import ru.nabokovsg.templates.dto.sections.NewReportSectionTemplateDto;

public interface SectionTemplateService {

    ReportTemplateDto save(NewReportSectionTemplateDto sectionTemplateDto);
}