package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.templates.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.UpdateSubsectionTemplateDto;
import ru.nabokovsg.templates.models.SubsectionTemplate;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubsectionTemplateMapper {

    List<SubsectionTemplate> mapToNewSubsectionTemplate(List<NewSubsectionTemplateDto> subsectionsDto);

    SubsectionTemplate mapToUpdateSubsectionTemplate(UpdateSubsectionTemplateDto subsectionDto);

    List<SubsectionTemplateDto> mapToSubsectionTemplateDto(List<SubsectionTemplate> subsections);
}