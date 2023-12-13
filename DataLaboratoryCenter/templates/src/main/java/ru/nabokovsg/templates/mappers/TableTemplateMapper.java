package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.templates.dto.table.NewTableTemplateDto;
import ru.nabokovsg.templates.dto.table.TableTemplateDto;
import ru.nabokovsg.templates.dto.table.UpdateTableTemplateDto;
import ru.nabokovsg.templates.models.ColumnHeaderTemplate;
import ru.nabokovsg.templates.models.TableTemplate;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TableTemplateMapper {

    @Mapping(source = "tableDto.tableDataType", target = "tableDataType")
    @Mapping(source = "tableDto.objectTypeId", target = "objectTypeId")
    @Mapping(source = "tableDto.reportingDocumentId", target = "reportingDocumentId")
    @Mapping(source = "tableDto.sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "tableDto.tableName", target = "tableName")
    @Mapping(source = "columnHeaders", target = "columnHeaders")
    TableTemplate mapToNewTableTemplate(NewTableTemplateDto tableDto, List<ColumnHeaderTemplate> columnHeaders);

    @Mapping(source = "tableDto.tableDataType", target = "tableDataType")
    @Mapping(source = "tableDto.sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "tableDto.tableName", target = "tableName")
    @Mapping(source = "columnHeaders", target = "columnHeaders")
    TableTemplate mapToUpdateTableTemplate(@MappingTarget TableTemplate table
                                                        , UpdateTableTemplateDto tableDto
                                                        , List<ColumnHeaderTemplate> columnHeaders);

    TableTemplateDto mapToTableTemplateDto(TableTemplate table);
}