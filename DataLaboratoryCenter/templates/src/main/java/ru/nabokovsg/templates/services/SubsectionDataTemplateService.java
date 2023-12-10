package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.subsectionDada.NewSubsectionDataTemplateDto;
import ru.nabokovsg.templates.dto.subsectionDada.UpdateSubsectionDataTemplateDto;
import ru.nabokovsg.templates.models.SubsectionDataTemplate;

import java.util.List;

public interface SubsectionDataTemplateService {

    List<SubsectionDataTemplate> save(List<NewSubsectionDataTemplateDto> subsectionDataDto);

    List<SubsectionDataTemplate> update(List<UpdateSubsectionDataTemplateDto> subsectionDataDto);
}