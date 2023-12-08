package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.templates.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.UpdateSubsectionTemplateDto;
import ru.nabokovsg.templates.models.SubsectionTemplate;

@Mapper(componentModel = "spring")
public interface SubsectionTemplateMapper {

    SubsectionTemplate mapToNewSubsectionTemplate(NewSubsectionTemplateDto subsectionDto);

    SubsectionTemplate mapToUpdateSubsectionTemplate(UpdateSubsectionTemplateDto subsectionsDto);

    SubsectionTemplateDto mapToSubsectionTemplateDto(SubsectionTemplate subsection);
}