package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.table.NewTableTemplateDto;
import ru.nabokovsg.templates.dto.table.TableTemplateDto;
import ru.nabokovsg.templates.dto.table.UpdateTableTemplateDto;
import ru.nabokovsg.templates.models.TableTemplate;

public interface TableTemplateService {

    TableTemplateDto save(NewTableTemplateDto tableDto);

    TableTemplateDto update(UpdateTableTemplateDto tableDto);

    TableTemplate getByTableDataType(String tableDataType);
}