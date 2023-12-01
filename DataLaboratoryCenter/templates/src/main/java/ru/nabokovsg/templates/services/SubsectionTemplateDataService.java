package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.subsection.subsectionData.NewSubsectionTemplateDataDto;
import ru.nabokovsg.templates.dto.subsection.subsectionData.SubsectionTemplateDataDto;
import ru.nabokovsg.templates.dto.subsection.subsectionData.UpdateSubsectionTemplateDataDto;

import java.util.List;

public interface SubsectionTemplateDataService {

    List<SubsectionTemplateDataDto> save(NewSubsectionTemplateDataDto subsectionData);

    List<SubsectionTemplateDataDto> update(UpdateSubsectionTemplateDataDto subsectionData);
}