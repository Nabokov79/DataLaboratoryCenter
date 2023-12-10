package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.table.NewTableTemplateDto;
import ru.nabokovsg.templates.dto.table.TableTemplateDto;
import ru.nabokovsg.templates.dto.table.UpdateTableTemplateDto;

public interface TableTemplateService {

    TableTemplateDto save(NewTableTemplateDto tableDto);

    TableTemplateDto update(UpdateTableTemplateDto tableDto);
}