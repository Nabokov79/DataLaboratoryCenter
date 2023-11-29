package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.templates.dto.sections.NewSectionTemplateDataDto;
import ru.nabokovsg.templates.dto.sections.SectionTemplateDto;
import ru.nabokovsg.templates.dto.sections.UpdateSectionTemplateDto;
import ru.nabokovsg.templates.models.SectionTemplate;

@Mapper(componentModel = "spring")
public interface SectionTemplateMapper {

   @Mapping(source = "objectTypeId", target = "objectTypeId")
   @Mapping(source = "reportingDocumentId", target = "reportingDocumentId")
   @Mapping(source = "sectionData.sequentialNumber", target = "sequentialNumber")
   @Mapping(source = "sectionData.sectionName", target = "sectionName")
   SectionTemplate mapToNewSectionTemplate(Long objectTypeId, Long reportingDocumentId, NewSectionTemplateDataDto sectionData);

   @Mapping(source = "sectionDto.sequentialNumber", target = "sequentialNumber")
   @Mapping(source = "sectionDto.sectionName", target = "sectionName")
   SectionTemplate mapToUpdateSectionTemplate(@MappingTarget SectionTemplate section, UpdateSectionTemplateDto sectionDto);

   SectionTemplateDto mapToSectionTemplateDto(SectionTemplate sectionDto);
}