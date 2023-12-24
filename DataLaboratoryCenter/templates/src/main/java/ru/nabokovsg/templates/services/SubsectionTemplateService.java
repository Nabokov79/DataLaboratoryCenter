package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.UpdateSubsectionTemplateDto;
import ru.nabokovsg.templates.models.TableTemplate;

public interface SubsectionTemplateService {

    SubsectionTemplateDto saveFromSectionTemplate(Long sectionId, NewSubsectionTemplateDto subsectionDto);

    SubsectionTemplateDto saveFromProtocolTemplate(Long protocolId, NewSubsectionTemplateDto subsectionDto);

    SubsectionTemplateDto update(UpdateSubsectionTemplateDto subsectionsDto);

    SubsectionTemplateDto get(Long id);

    void delete(Long id);

    boolean existsById(Long id);

    void saveWithTable(Long subsectionId, TableTemplate table);
}