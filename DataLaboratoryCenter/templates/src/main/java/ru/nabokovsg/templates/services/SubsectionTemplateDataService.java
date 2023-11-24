package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.subsection.subsectionData.NewSubsectionTemplateDataDto;
import ru.nabokovsg.templates.dto.subsection.subsectionData.UpdateSubsectionTemplateDataDto;
import ru.nabokovsg.templates.models.SubsectionTemplateData;

import java.util.List;

public interface SubsectionTemplateDataService {

    List<SubsectionTemplateData> save(List<NewSubsectionTemplateDataDto> subsectionsData);

    List<SubsectionTemplateData> update(UpdateSubsectionTemplateDataDto subsectionData);
}