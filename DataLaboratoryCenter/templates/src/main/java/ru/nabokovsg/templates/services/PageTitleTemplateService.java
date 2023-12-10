package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.UpdatePageTitleTemplateDto;

import java.util.List;

public interface PageTitleTemplateService {

    PageTitleTemplateDto save(NewPageTitleTemplateDto pageTitleDto);

    PageTitleTemplateDto update(UpdatePageTitleTemplateDto pageTitleDto);

    PageTitleTemplateDto get(Long id);

    List<PageTitleTemplateDto> getAll(Long reportingDocumentId);
}