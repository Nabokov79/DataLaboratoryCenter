package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.subsectionDada.*;
import ru.nabokovsg.templates.models.SubsectionDataTemplate;

import java.util.List;

public interface SubsectionDataTemplateService {

    SubsectionDataTemplateDto saveDivisionData(NewDivisionDataDto divisionDataDto);

    List<SubsectionDataTemplateDto> saveDocumentationData(NewDocumentationDataDto documentationDataDto);

    List<SubsectionDataTemplateDto> saveMeasuringToolData(List<NewMeasuringToolDataDto> measuringToolDataDto);

    List<SubsectionDataTemplate> getAllById(List<Long> ids);
}