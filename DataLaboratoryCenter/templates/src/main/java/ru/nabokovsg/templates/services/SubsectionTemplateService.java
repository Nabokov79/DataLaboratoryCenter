package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.UpdateSubsectionTemplateDto;

public interface SubsectionTemplateService {

    SubsectionTemplateDto save(NewSubsectionTemplateDto subsectionsDto);

    SubsectionTemplateDto update(UpdateSubsectionTemplateDto subsectionsDto);

    void delete(Long id);
}