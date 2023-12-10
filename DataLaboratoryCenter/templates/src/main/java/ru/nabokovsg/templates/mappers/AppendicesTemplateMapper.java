package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.templates.dto.appendices.AppendicesTemplateDto;
import ru.nabokovsg.templates.dto.appendices.NewAppendicesTemplateDto;
import ru.nabokovsg.templates.dto.appendices.UpdateAppendicesTemplateDto;
import ru.nabokovsg.templates.models.AppendicesTemplate;

@Mapper(componentModel = "spring")
public interface AppendicesTemplateMapper {

    AppendicesTemplate mapToNewAppendicesTemplate(NewAppendicesTemplateDto appendicesDto);

    AppendicesTemplate mapToUpdateAppendicesTemplate(UpdateAppendicesTemplateDto appendicesDto);

    AppendicesTemplateDto mapToAppendicesTemplateDto(AppendicesTemplate appendices);
}