package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.templates.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.UpdateSubsectionTemplateDto;
import ru.nabokovsg.templates.models.SubsectionDataTemplate;
import ru.nabokovsg.templates.models.SubsectionTemplate;
import ru.nabokovsg.templates.models.TableTemplate;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubsectionTemplateMapper {

    SubsectionTemplate mapToNewSubsectionTemplate(NewSubsectionTemplateDto subsectionDto);

    SubsectionTemplate mapToUpdateSubsectionTemplate(UpdateSubsectionTemplateDto subsectionsDto);

    SubsectionTemplateDto mapToSubsectionTemplateDto(SubsectionTemplate subsection);

    @Mapping(source = "subsectionData", target = "subsectionData")
    SubsectionTemplate mapSubsectionData(@MappingTarget SubsectionTemplate subsection
                                                      , List<SubsectionDataTemplate> subsectionData);

    @Mapping(source = "table", target = "table")
    SubsectionTemplate mapTableTemplate(@MappingTarget SubsectionTemplate subsection, TableTemplate table);
}