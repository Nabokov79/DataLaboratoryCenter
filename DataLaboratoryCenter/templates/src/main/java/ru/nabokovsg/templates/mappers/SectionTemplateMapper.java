package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.templates.dto.sections.NewSectionTemplateDto;
import ru.nabokovsg.templates.models.SectionTemplate;

@Mapper(componentModel = "spring")
public interface SectionTemplateMapper {

   SectionTemplate mapToNewSectionTemplate(NewSectionTemplateDto sectionDto);
}