package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.templates.dto.subsection.subsectionData.NewSubsectionTemplateDataDto;
import ru.nabokovsg.templates.dto.subsection.subsectionData.SubsectionDataDto;
import ru.nabokovsg.templates.dto.subsection.subsectionData.UpdateSubsectionTemplateDataDto;

@Mapper(componentModel = "spring")
public interface SubsectionDataTemplateMapper {

    SubsectionDataDto mapToSubsectionDataForUpdate(UpdateSubsectionTemplateDataDto subsectionDataDto);

    SubsectionDataDto mapToSubsectionDataForNew(NewSubsectionTemplateDataDto subsectionDataDto);
}