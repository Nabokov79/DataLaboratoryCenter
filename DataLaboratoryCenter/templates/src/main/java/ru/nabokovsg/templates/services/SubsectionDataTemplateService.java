package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.subsectionDada.*;
import ru.nabokovsg.templates.models.SubsectionDataTemplate;

import javax.validation.Valid;
import java.util.List;

public interface SubsectionDataTemplateService {

    SubsectionDataTemplate saveDivisionData(@Valid DivisionDataDto divisionDataDto);

    List<SubsectionDataTemplate> saveDocumentationData(@Valid DocumentationDataDto documentationDataDto);

    List<SubsectionDataTemplate> saveMeasuringToolData(@Valid List<MeasuringToolDataDto> measuringToolDataDto);

    List<SubsectionDataTemplate> getAllById(List<Long> ids);
}