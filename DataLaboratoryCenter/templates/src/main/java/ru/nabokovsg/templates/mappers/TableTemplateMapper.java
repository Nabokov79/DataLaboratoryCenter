package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.templates.dto.table.NewTableTemplateDto;
import ru.nabokovsg.templates.dto.table.ShortTableTemplateDto;
import ru.nabokovsg.templates.dto.table.TableTemplateDto;
import ru.nabokovsg.templates.dto.table.UpdateTableTemplateDto;
import ru.nabokovsg.templates.models.ColumnHeaderTemplate;
import ru.nabokovsg.templates.models.TableTemplate;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TableTemplateMapper {

    @Mapping(source = "tableDataType", target = "tableDataType")
    @Mapping(source = "sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "tableName", target = "tableName")
    @Mapping(source = "columnHeaders", target = "columnHeaders")
    @Mapping(target = "id", ignore = true)
    TableTemplate mapToNewTableTemplate(NewTableTemplateDto tableDto, List<ColumnHeaderTemplate> columnHeaders);

    @Mapping(source = "tableDataType", target = "tableDataType")
    @Mapping(source = "sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "tableName", target = "tableName")
    @Mapping(source = "columnHeaders", target = "columnHeaders")
    @Mapping(source = "id", target = "id")
    TableTemplate mapToUpdateTableTemplate(UpdateTableTemplateDto tableDto , List<ColumnHeaderTemplate> columnHeaders);

    TableTemplateDto mapToTableTemplateDto(TableTemplate table);
}