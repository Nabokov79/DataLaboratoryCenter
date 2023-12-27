package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.conclusion.ConclusionTemplateDto;
import ru.nabokovsg.templates.dto.conclusion.NewConclusionTemplateDto;
import ru.nabokovsg.templates.dto.conclusion.UpdateConclusionTemplateDto;
import ru.nabokovsg.templates.models.ConclusionTemplate;

public interface ConclusionTemplateService {

    ConclusionTemplateDto save(NewConclusionTemplateDto conclusionDto);

    ConclusionTemplateDto update(UpdateConclusionTemplateDto conclusionDto);

    ConclusionTemplate getByReportingDocumentId(Long reportingDocumentId);

    void delete(Long id);
}