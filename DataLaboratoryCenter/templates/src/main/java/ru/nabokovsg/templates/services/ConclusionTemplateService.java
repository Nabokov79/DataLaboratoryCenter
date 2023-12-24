package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.conclusion.ConclusionTemplateDto;
import ru.nabokovsg.templates.dto.conclusion.NewConclusionTemplateDto;
import ru.nabokovsg.templates.dto.conclusion.UpdateConclusionTemplateDto;

public interface ConclusionTemplateService {

    ConclusionTemplateDto save(NewConclusionTemplateDto conclusionDto);

    ConclusionTemplateDto update(UpdateConclusionTemplateDto conclusionDto);

    void delete(Long id);
}