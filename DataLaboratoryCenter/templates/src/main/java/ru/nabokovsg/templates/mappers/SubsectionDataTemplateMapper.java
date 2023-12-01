package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.templates.dto.subsection.subsectionData.NewSubsectionTemplateDataDto;
import ru.nabokovsg.templates.dto.subsection.subsectionData.SubsectionDataDto;
import ru.nabokovsg.templates.dto.subsection.subsectionData.SubsectionTemplateDataDto;
import ru.nabokovsg.templates.dto.subsection.subsectionData.UpdateSubsectionTemplateDataDto;
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

    SubsectionDataDto mapToUpdateSubsectionDataDto(UpdateSubsectionTemplateDataDto subsectionDataDto);

    SubsectionDataDto mapToNewSubsectionDataDto(NewSubsectionTemplateDataDto subsectionDataDto);
}