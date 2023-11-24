package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.UpdateSubsectionTemplateDto;
import ru.nabokovsg.templates.models.SubsectionTemplate;

import java.util.List;
import java.util.Set;

public interface SubsectionTemplateService {

    List<SubsectionTemplateDto> save(List<NewSubsectionTemplateDto> subsectionsDto);

    List<SubsectionTemplateDto> update(UpdateSubsectionTemplateDto subsectionsDto);

   Set<SubsectionTemplate> getAllForSectionTemplate(Long sectionId);
}