package ru.nabokovsg.templates.services;

import org.springframework.validation.annotation.Validated;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDto;
import ru.nabokovsg.templates.dto.header.NewHeaderTemplateDto;
import ru.nabokovsg.templates.dto.header.UpdateHeaderTemplateDto;
import ru.nabokovsg.templates.models.HeaderTemplate;

@Validated
public interface HeaderTemplateService {

    HeaderTemplateDto save(NewHeaderTemplateDto headerDto);

    HeaderTemplateDto update(UpdateHeaderTemplateDto headerDto);

    HeaderTemplate getByReportingDocumentId(Long reportingDocumentId);
}