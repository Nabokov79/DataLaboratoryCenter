package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDataDto;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDto;
import ru.nabokovsg.templates.dto.header.NewHeaderTemplateDto;
import ru.nabokovsg.templates.dto.header.UpdateHeaderTemplateDto;
import ru.nabokovsg.templates.models.HeaderTemplate;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HeaderTemplateMapper {

    HeaderTemplate mapToHeader(HeaderTemplate header);

    HeaderTemplateDto mapToHeaderTemplateDto(HeaderTemplate header);

    HeaderTemplateDataDto mapToNewHeaderTemplateDataDto(NewHeaderTemplateDto headerDto);

    HeaderTemplateDataDto mapToUpdateHeaderTemplateDataDto(UpdateHeaderTemplateDto headerDto);
}