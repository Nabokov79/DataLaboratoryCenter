package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.tables.*;
import ru.nabokovsg.templates.models.TableTemplate;

import java.util.List;

public interface TableTemplateService {

    TableTemplateDto saveForProtocol(NewProtocolTableTemplateDto tableDto);

   TableTemplateDto saveForSubsection(NewSubsectionTableTemplateDto tableDto);

    TableTemplateDto update(UpdateTableTemplateDto tableDto);

    List<ShortTableTemplateDto> getAll();

    TableTemplate getById(Long id);
    List<TableTemplate> getAllById(List<Long> ids);
}