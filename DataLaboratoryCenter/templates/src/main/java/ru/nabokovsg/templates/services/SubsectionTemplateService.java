package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.UpdateSubsectionTemplateDto;
import ru.nabokovsg.templates.models.SubsectionTemplateData;

import java.util.List;

public interface SubsectionTemplateService {

    List<SubsectionTemplateDto> save(List<NewSubsectionTemplateDto> subsectionsDto);

    List<SubsectionTemplateDto> update(UpdateSubsectionTemplateDto subsectionsDto);

    List<SubsectionTemplateDto> getAll(Long sectionId);

    void addSubsectionTemplateData(Long subsectionId, List<SubsectionTemplateData> subsectionTemplateData);
}