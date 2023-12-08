package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.subsectionData.NewDivisionDataDto;
import ru.nabokovsg.templates.dto.subsectionData.NewDocumentationDataDto;
import ru.nabokovsg.templates.dto.subsectionData.NewMeasuringToolDataDto;
import ru.nabokovsg.templates.dto.subsectionData.SubsectionTemplateDataDto;
import java.util.List;

public interface SubsectionTemplateDataService {

    SubsectionTemplateDataDto saveDivisionData(NewDivisionDataDto divisionDataDto);

    List<SubsectionTemplateDataDto> saveDocumentationData(NewDocumentationDataDto documentationDataDto);

    List<SubsectionTemplateDataDto> saveMeasuringToolData(Long subsectionId
                                                        , List<NewMeasuringToolDataDto> measuringToolDataDto);
}