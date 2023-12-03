package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.templates.dto.subsectionData.NewDivisionDataDto;
import ru.nabokovsg.templates.dto.subsectionData.NewDocumentationDataDto;
import ru.nabokovsg.templates.dto.subsectionData.SubsectionTemplateDataDto;
import ru.nabokovsg.templates.dto.subsectionData.NewMeasuringToolDataDto;
import ru.nabokovsg.templates.models.SubsectionTemplateData;
import ru.nabokovsg.templates.models.enums.SubsectionDataType;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubsectionDataTemplateMapper {

    List<SubsectionTemplateDataDto> mapToSubsectionDataDto(List<SubsectionTemplateData> subsectionData);

    @Mapping(source = "subsectionDataType", target = "subsectionDataType")
    @Mapping(source = "objectTypeId", target = "objectTypeId")
    @Mapping(source = "subsectionData", target = "subsectionData")
    SubsectionTemplateData mapToSubsectionData(SubsectionDataType subsectionDataType, Long objectTypeId, String subsectionData);

    @Mapping(source = "subsectionDataType", target = "subsectionDataType")
    @Mapping(source = "subsectionData", target = "subsectionData")
    SubsectionTemplateData mapForDivisionData(SubsectionDataType subsectionDataType, String subsectionData);

    NewDocumentationDataDto mapToUpdateSubsectionDataDto(NewMeasuringToolDataDto subsectionDataDto);

    NewDocumentationDataDto mapToNewSubsectionDataDto(NewDivisionDataDto subsectionDataDto);
}