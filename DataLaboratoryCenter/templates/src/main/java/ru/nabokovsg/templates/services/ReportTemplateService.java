package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.pageTitle.ShortPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.section.ShortSectionTemplateDto;
import ru.nabokovsg.templates.models.PageTitleTemplate;
import ru.nabokovsg.templates.models.SectionTemplate;

import java.util.List;

public interface ReportTemplateService {

    List<ShortPageTitleTemplateDto> getAll();

    List<ShortSectionTemplateDto> getAllSections(Long id);

    void saveWithPageTitleTemplate(PageTitleTemplate pageTitle);

    void saveWithSectionTemplate(Long reportId, List<SectionTemplate> sections);

    boolean existsById(Long id);
}