package ru.nabokovsg.templates.services.factory;

import ru.nabokovsg.templates.client.dto.DocumentationDto;
import ru.nabokovsg.templates.models.SubsectionTemplateData;
import ru.nabokovsg.templates.models.enums.DivisionType;
import ru.nabokovsg.templates.models.enums.SubsectionDataType;

import java.util.List;

public interface SubsectionDataFactory {

    List<SubsectionTemplateData> createByDocumentationData(SubsectionDataType type, List<DocumentationDto> documentations);

    List<SubsectionTemplateData> createByDivisionData(DivisionType divisionType, Long divisionId, String divisionName);
}