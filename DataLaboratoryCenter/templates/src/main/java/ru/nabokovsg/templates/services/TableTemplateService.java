package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.table.NewTableTemplateDto;
import ru.nabokovsg.templates.dto.table.TableTemplateDto;
import ru.nabokovsg.templates.dto.table.UpdateTableTemplateDto;

public interface TableTemplateService {

    TableTemplateDto saveFromSubsectionTemplate(Long subsectionId, NewTableTemplateDto tableDto);

    TableTemplateDto saveFromProtocolTemplate(Long protocolId, NewTableTemplateDto tableDto);

    TableTemplateDto update(UpdateTableTemplateDto tableDto);

    TableTemplateDto get(Long id);

    void delete(Long id);
}