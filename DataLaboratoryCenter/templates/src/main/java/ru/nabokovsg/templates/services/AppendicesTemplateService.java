package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.appendices.AppendicesTemplateDto;
import ru.nabokovsg.templates.dto.appendices.NewAppendicesTemplateDto;
import ru.nabokovsg.templates.dto.appendices.UpdateAppendicesTemplateDto;

public interface AppendicesTemplateService {

    AppendicesTemplateDto save(NewAppendicesTemplateDto appendicesDto);

    AppendicesTemplateDto update(UpdateAppendicesTemplateDto appendicesDto);
}