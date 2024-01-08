package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.subsectionDada.DivisionDataDto;
import ru.nabokovsg.templates.dto.subsectionDada.DocumentationDataDto;
import ru.nabokovsg.templates.dto.subsectionDada.MeasuringToolDataDto;
import ru.nabokovsg.templates.models.DocumentationTemplate;
import ru.nabokovsg.templates.models.MeasuringToolTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

public interface SubsectionDataProcessingService {

    List<DocumentationTemplate> getDocumentationData(@Valid DocumentationDataDto documentationDataDto);

    List<MeasuringToolTemplate> getMeasuringToolData(@Valid List<MeasuringToolDataDto> measuringToolDataDto);

    String getDivisionData(@Valid DivisionDataDto divisionDataDto);

    String getCertificateData(@NotNull @Positive Long employeeId);
}