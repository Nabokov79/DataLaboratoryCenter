package ru.nabokovsg.templates.services;

import org.springframework.validation.annotation.Validated;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDto;
import ru.nabokovsg.templates.dto.header.NewHeaderTemplateDto;
import ru.nabokovsg.templates.dto.header.UpdateHeaderTemplateDto;
import java.util.List;

@Validated
public interface HeaderTemplateService {

    List<HeaderTemplateDto> save(NewHeaderTemplateDto headerDto);

    HeaderTemplateDto update(UpdateHeaderTemplateDto headerDto);

    HeaderTemplateDto get(Long reportingDocumentId);
}