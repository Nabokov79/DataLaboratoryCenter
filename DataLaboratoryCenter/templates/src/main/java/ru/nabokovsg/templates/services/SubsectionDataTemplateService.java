package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.subsectionDada.*;
import ru.nabokovsg.templates.models.SubsectionDataTemplate;

import javax.validation.Valid;
import java.util.List;

public interface SubsectionDataTemplateService {

    SubsectionDataTemplateDto saveDivisionData(@Valid NewDivisionDataDto divisionDataDto);

    List<SubsectionDataTemplateDto> saveDocumentationData(@Valid NewDocumentationDataDto documentationDataDto);

    List<SubsectionDataTemplateDto> saveMeasuringToolData(@Valid List<NewMeasuringToolDataDto> measuringToolDataDto);

    List<SubsectionDataTemplate> getAllById(List<Long> ids);
}