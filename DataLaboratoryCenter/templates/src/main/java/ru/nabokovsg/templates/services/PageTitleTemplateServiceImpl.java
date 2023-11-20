package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.TemplateClient;
import ru.nabokovsg.templates.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.UpdatePageTitleTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.PageTitleTemplateMapper;
import ru.nabokovsg.templates.models.PageTitleTemplate;
import ru.nabokovsg.templates.models.builder.TemplateData;
import ru.nabokovsg.templates.models.enums.DataType;
import ru.nabokovsg.templates.repository.PageTitleTemplateRepository;
import ru.nabokovsg.templates.services.factory.StringFactory;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PageTitleTemplateServiceImpl implements PageTitleTemplateService {

    private final PageTitleTemplateRepository repository;
    private final PageTitleTemplateMapper mapper;
    private final TemplateClient client;
    private final StringFactory factory;
    private final HeaderTemplateService service;

    @Override
    public PageTitleTemplateDto save(NewPageTitleTemplateDto pageTitleDto) {
        PageTitleTemplate pageTitle = repository.findByReportingDocumentId(pageTitleDto.getReportingDocumentId());
        if (pageTitle == null) {
            String signature = factory.create(new TemplateData.Builder()
                                                             .type(DataType.SIGNATURE)
                                                             .employee(client.getEmployee(pageTitleDto.getEmployeeId()))
                                                             .build());
            pageTitle = repository.save(mapper.mapToPageTitleTemplate(
                                          mapper.mapToNewPageTitleTemplate(pageTitleDto)
                                        , service.getByReportingDocumentId(pageTitleDto.getReportingDocumentId())
                                        , signature
                                        , String.valueOf((LocalDate.now().getYear()))));
        }
        return mapper.mapToPageTitleTemplateDto(pageTitle);
    }

    @Override
    public PageTitleTemplateDto update(UpdatePageTitleTemplateDto pageTitleDto) {
        if (repository.existsByReportingDocumentId(pageTitleDto.getReportingDocumentId())) {
            String signature = factory.create(new TemplateData.Builder()
                                                             .type(DataType.SIGNATURE)
                                                             .employee(client.getEmployee(pageTitleDto.getEmployeeId()))
                                                             .build());
            return mapper.mapToPageTitleTemplateDto(repository.save(mapper.mapToPageTitleTemplate(
                                                  mapper.mapToUpdatePageTitleTemplate(pageTitleDto)
                                                , service.getByReportingDocumentId(pageTitleDto.getReportingDocumentId())
                                                , signature
                                                , String.valueOf((LocalDate.now().getYear())))));
        }
        throw new NotFoundException(String.format("PageTitleTemplate with id=%s not found", pageTitleDto.getId()));
    }
}