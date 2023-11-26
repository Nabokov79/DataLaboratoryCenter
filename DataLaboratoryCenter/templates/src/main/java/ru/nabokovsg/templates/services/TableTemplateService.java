package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.tables.NewTableTemplateDto;
import ru.nabokovsg.templates.dto.tables.ShortTableTemplateDto;
import ru.nabokovsg.templates.dto.tables.TableTemplateDto;
import ru.nabokovsg.templates.dto.tables.UpdateTableTemplateDto;

import java.util.List;

public interface TableTemplateService {

    TableTemplateDto save(NewTableTemplateDto tableDto);

    TableTemplateDto update(UpdateTableTemplateDto tableDto);

    List<ShortTableTemplateDto> getAll();
}