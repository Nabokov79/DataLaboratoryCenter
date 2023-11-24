package ru.nabokovsg.templates.services;

import org.springframework.web.bind.annotation.PathVariable;
import ru.nabokovsg.templates.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.UpdatePageTitleTemplateDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public interface PageTitleTemplateService {

   PageTitleTemplateDto save(NewPageTitleTemplateDto pageTitleDto);

   PageTitleTemplateDto update(UpdatePageTitleTemplateDto pageTitleDto);

   PageTitleTemplateDto get(Long reportingDocumentId);
}