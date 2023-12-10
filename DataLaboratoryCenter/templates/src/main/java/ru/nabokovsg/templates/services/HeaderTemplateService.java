package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.header.HeaderTemplateDto;
import ru.nabokovsg.templates.dto.header.NewHeaderTemplateDto;
import ru.nabokovsg.templates.dto.header.UpdateHeaderTemplateDto;

public interface HeaderTemplateService {

    HeaderTemplateDto save(NewHeaderTemplateDto headerDto);

    HeaderTemplateDto update(UpdateHeaderTemplateDto headerDto);

    HeaderTemplateDto get(Long reportingDocumentId);
}