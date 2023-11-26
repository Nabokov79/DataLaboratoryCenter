package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.tables.columns.NewColumnHeaderTemplateDto;
import ru.nabokovsg.templates.dto.tables.columns.UpdateColumnHeaderTemplateDto;
import ru.nabokovsg.templates.models.ColumnHeaderTemplate;

import java.util.List;

public interface ColumnHeaderTemplateService {

    List<ColumnHeaderTemplate> save(List<NewColumnHeaderTemplateDto> templatesDto);

    List<ColumnHeaderTemplate> update(List<UpdateColumnHeaderTemplateDto> templatesDto);
}