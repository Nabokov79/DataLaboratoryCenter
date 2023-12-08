package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.templates.dto.tables.*;
import ru.nabokovsg.templates.models.TableTemplate;

@Mapper(componentModel = "spring")
public interface TableTemplateMapper {

    TableTemplate mapToNewProtocolTableTemplate(NewProtocolTableTemplateDto tableDto);

    TableTemplate mapToNewSubsectionTableTemplate(NewSubsectionTableTemplateDto tableDto);

    TableTemplate mapToUpdateTableTemplate(UpdateTableTemplateDto tableTemplateDto);

    TableTemplateDto mapToTableTemplateDto(TableTemplate tableTemplate);

    ShortTableTemplateDto mapToShortTableTemplateDto(TableTemplate tableTemplate);
}