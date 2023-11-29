package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.sections.SectionTemplateDto;
import ru.nabokovsg.templates.dto.sections.NewSectionTemplateDto;
import ru.nabokovsg.templates.dto.sections.UpdateSectionTemplateDto;
import ru.nabokovsg.templates.models.SectionTemplate;

import java.util.List;
import java.util.Set;

public interface SectionTemplateService {

    List<SectionTemplateDto> save(NewSectionTemplateDto sectionsDto);

    List<SectionTemplateDto> update(List<UpdateSectionTemplateDto> sectionsDto);

    List<SectionTemplateDto> getAll(Long objectTypeId, Long reportingDocumentId);

    Set<SectionTemplate> create(Long objectTypeId, Long reportingDocumentId);
}