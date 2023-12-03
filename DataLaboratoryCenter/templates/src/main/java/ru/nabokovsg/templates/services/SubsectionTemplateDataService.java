package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.subsectionData.NewDivisionDataDto;
import ru.nabokovsg.templates.dto.subsectionData.NewDocumentationDataDto;
import ru.nabokovsg.templates.dto.subsectionData.NewMeasuringToolDataDto;
import ru.nabokovsg.templates.dto.subsectionData.SubsectionTemplateDataDto;

public interface SubsectionTemplateDataService {

    SubsectionTemplateDataDto saveDivisionData(NewDivisionDataDto divisionDataDto);

    SubsectionTemplateDataDto saveDocumentationData(NewDocumentationDataDto documentationDataDto);

    SubsectionTemplateDataDto saveMeasuringToolData(NewMeasuringToolDataDto measuringToolDataDto);
}