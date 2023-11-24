package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.templates.dto.sections.NewSectionTemplateDataDto;
import ru.nabokovsg.templates.dto.sections.SectionTemplateDto;
import ru.nabokovsg.templates.dto.sections.UpdateSectionTemplateDataDto;
import ru.nabokovsg.templates.models.SectionTemplate;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SectionTemplateMapper {

   List<SectionTemplate> mapToNewSectionTemplate(List<NewSectionTemplateDataDto> sectionsData);

   SectionTemplate mapToUpdateSectionTemplate(UpdateSectionTemplateDataDto sectionData);

   SectionTemplateDto mapToSectionTemplateDto(SectionTemplate sectionDto);
}