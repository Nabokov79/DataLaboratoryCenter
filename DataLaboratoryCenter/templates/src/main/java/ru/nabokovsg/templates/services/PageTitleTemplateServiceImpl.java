package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.TemplateClient;
import ru.nabokovsg.templates.client.dto.ReportingDocumentDto;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.UpdatePageTitleTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.PageTitleTemplateMapper;
import ru.nabokovsg.templates.models.PageTitleTemplate;
import ru.nabokovsg.templates.repository.PageTitleTemplateRepository;
import ru.nabokovsg.templates.services.builders.StringBuilderService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PageTitleTemplateServiceImpl implements PageTitleTemplateService {

    private final PageTitleTemplateRepository repository;
    private final PageTitleTemplateMapper mapper;
    private final TemplateClient client;
    private final HeaderTemplateService headerService;
    private final StringBuilderService stringBuilder;

    @Override
    public PageTitleTemplateDto save(NewPageTitleTemplateDto pageTitleDto) {
        PageTitleTemplate pageTitle = repository.findByObjectTypeIdAndReportingDocumentId(pageTitleDto.getObjectTypeId()
                                                                               , pageTitleDto.getReportingDocumentId());
        if (pageTitle == null) {
            String year = String.valueOf((LocalDate.now().getYear()));
            HeaderTemplateDto header = headerService.get(pageTitleDto.getReportingDocumentId());
            ReportingDocumentDto reportingDocument = client.getReportingDocument(pageTitleDto.getReportingDocumentId());
            String signature = stringBuilder.convertEmployee(client.getEmployee(pageTitleDto.getEmployeeId()));
            pageTitle = repository.save(mapper.mapToNewPageTitleTemplate(pageTitleDto, header, reportingDocument
                                                                                               , signature, year));
        }
        return mapper.mapToPageTitleTemplateDto(pageTitle);
    }

    @Override
    public PageTitleTemplateDto update(UpdatePageTitleTemplateDto pageTitleDto) {
        PageTitleTemplate pageTitle = repository.findById(pageTitleDto.getId()).orElseThrow(() -> new NotFoundException(
                String.format("PageTitleTemplate id=%s not found for update",pageTitleDto.getId())));
        String year = String.valueOf((LocalDate.now().getYear()));
        HeaderTemplateDto header = headerService.get(pageTitleDto.getReportingDocumentId());
        ReportingDocumentDto reportingDocument = client.getReportingDocument(pageTitleDto.getReportingDocumentId());
        String signature = stringBuilder.convertEmployee(client.getEmployee(pageTitleDto.getEmployeeId()));
        return mapper.mapToPageTitleTemplateDto(
                repository.save(mapper.mapToUpdatePageTitleTemplate(pageTitle.getId(), header, reportingDocument
                                                                                               , signature, year))
        );
    }

    @Override
    public PageTitleTemplateDto get(Long id) {
        return mapper.mapToPageTitleTemplateDto(repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("PageTitleTemplate id=%s not found",id))));
    }

    @Override
    public List<PageTitleTemplateDto> getAll(Long reportingDocumentId) {
        return repository.findAllByReportingDocumentId(reportingDocumentId).stream()
                .map(mapper::mapToPageTitleTemplateDto)
                .toList();
    }
}