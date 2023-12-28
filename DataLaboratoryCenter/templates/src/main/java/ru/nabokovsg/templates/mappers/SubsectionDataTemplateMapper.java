package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.templates.dto.subsectionDada.DivisionDataParam;
import ru.nabokovsg.templates.dto.subsectionDada.NewDivisionDataDto;
import ru.nabokovsg.templates.models.SubsectionDataTemplate;

@Mapper(componentModel = "spring")
public interface SubsectionDataTemplateMapper {

    SubsectionDataTemplate mapToSubsectionDataTemplate(String valueData);

    DivisionDataParam mapToDivisionDataParam(NewDivisionDataDto divisionDataDto);
}