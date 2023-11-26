package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.templates.dto.tables.NewTableTemplateDto;
import ru.nabokovsg.templates.dto.tables.ShortTableTemplateDto;
import ru.nabokovsg.templates.dto.tables.TableTemplateDto;
import ru.nabokovsg.templates.dto.tables.UpdateTableTemplateDto;
import ru.nabokovsg.templates.models.TableTemplate;

@Mapper(componentModel = "spring")
public interface TableTemplateMapper {

    TableTemplate mapToNewTableTemplate(NewTableTemplateDto tableTemplateDto);

    TableTemplate mapToUpdateTableTemplate(UpdateTableTemplateDto tableTemplateDto);

    TableTemplateDto mapToTableTemplateDto(TableTemplate tableTemplate);

    ShortTableTemplateDto mapToShortTableTemplateDto(TableTemplate tableTemplate);
}