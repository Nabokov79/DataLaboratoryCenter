package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.templates.dto.subsectionData.DivisionDataParam;
import ru.nabokovsg.templates.dto.subsectionData.NewDivisionDataDto;
import ru.nabokovsg.templates.dto.subsectionData.SubsectionTemplateDataDto;
import ru.nabokovsg.templates.models.SubsectionTemplateData;
import ru.nabokovsg.templates.models.enums.SubsectionDataType;

@Mapper(componentModel = "spring")
public interface SubsectionDataTemplateMapper {

    SubsectionTemplateDataDto mapToSubsectionDataDto(SubsectionTemplateData subsectionData);
    @Mapping(source = "subsectionDataType", target = "subsectionDataType")
    @Mapping(source = "objectTypeId", target = "objectTypeId")
    @Mapping(source = "subsectionData", target = "subsectionData")
    SubsectionTemplateData mapToSubsectionData(SubsectionDataType subsectionDataType
                                             , Long objectTypeId
                                             , String subsectionData);

    @Mapping(source = "subsectionDataType", target = "subsectionDataType")
    @Mapping(source = "toll", target = "subsectionData")
    SubsectionTemplateData mapFromNewMeasuringToolDataDto(SubsectionDataType subsectionDataType, String toll);

    @Mapping(source = "subsectionDataType", target = "subsectionDataType")
    @Mapping(source = "divisionId", target = "divisionId")
    @Mapping(source = "subsectionData", target = "subsectionData")
    SubsectionTemplateData mapForDivisionData(SubsectionDataType subsectionDataType
                                            , Long divisionId
                                            , String subsectionData);

    DivisionDataParam mapToDivisionDataParam(NewDivisionDataDto divisionDataDto);
}