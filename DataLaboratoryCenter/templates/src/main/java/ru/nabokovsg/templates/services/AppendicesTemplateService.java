package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.appendices.AppendicesTemplateDto;
import ru.nabokovsg.templates.dto.appendices.NewAppendicesTemplateDto;
import ru.nabokovsg.templates.dto.appendices.UpdateAppendicesTemplateDto;
import ru.nabokovsg.templates.models.AppendicesTemplate;
import java.util.Set;

public interface AppendicesTemplateService {

    AppendicesTemplateDto save(NewAppendicesTemplateDto appendicesDto);

    AppendicesTemplateDto update(UpdateAppendicesTemplateDto appendicesDto);

    void delete(Long id);

    Set<AppendicesTemplate> getByObjectTypeId(Long objectTypeId);
}