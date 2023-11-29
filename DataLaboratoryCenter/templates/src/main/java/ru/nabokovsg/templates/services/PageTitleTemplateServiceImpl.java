package ru.nabokovsg.templates.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
import ru.nabokovsg.templates.models.QPageTitleTemplate;
import ru.nabokovsg.templates.models.builder.TemplateData;
import ru.nabokovsg.templates.models.enums.DataType;
import ru.nabokovsg.templates.repository.PageTitleTemplateRepository;
import ru.nabokovsg.templates.services.factory.StringFactory;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PageTitleTemplateServiceImpl implements PageTitleTemplateService {

    private final PageTitleTemplateRepository repository;
    private final PageTitleTemplateMapper mapper;
    private final TemplateClient client;
    private final StringFactory factory;
    private final HeaderTemplateService headerService;
    private final EntityManager entityManager;

    @Override
    public List<PageTitleTemplateDto> save(NewPageTitleTemplateDto pageTitleDto) {
        Map<Long, PageTitleTemplate> pageTitlesDb = getAllByReportingDocumentIdAndObjectTypeIds(pageTitleDto.getReportingDocumentId()
                , pageTitleDto.getObjectTypeIds()).stream().collect(Collectors.toMap(PageTitleTemplate::getObjectTypeId, p -> p));
        List<Long> objectTypeIds = pageTitleDto.getObjectTypeIds().stream().filter(id -> !pageTitlesDb.containsKey(id)).toList();
        if (objectTypeIds.isEmpty()) {
            return pageTitlesDb.values().stream().map(mapper::mapToPageTitleTemplateDto).toList();
        }
        String year = String.valueOf((LocalDate.now().getYear()));
        HeaderTemplateDto header = headerService.get(pageTitleDto.getReportingDocumentId());
        ReportingDocumentDto reportingDocument = client.getReportingDocument(pageTitleDto.getReportingDocumentId());
        String signature = factory.create(new TemplateData.Builder()
                .type(DataType.SIGNATURE)
                .employee(client.getEmployee(pageTitleDto.getEmployeeId()))
                .build());
        List<PageTitleTemplate> pageTitles = objectTypeIds.stream()
                .map(id -> {
                    PageTitleTemplate pageTitle = mapper.mapToPageTitleTemplate(
                            mapper.mapToNewPageTitleTemplate(pageTitleDto), header, reportingDocument, signature, year);
                    pageTitle.setObjectTypeId(id);
                    return pageTitle;
                         })
                .toList();
       return repository.saveAll(pageTitles).stream().map(mapper::mapToPageTitleTemplateDto).toList();

    }

    @Override
    public PageTitleTemplateDto update(UpdatePageTitleTemplateDto pageTitleDto) {
        if (repository.existsByReportingDocumentIdAndObjectTypeId(pageTitleDto.getReportingDocumentId()
                                                               , pageTitleDto.getObjectTypeId())) {
            String signature = factory.create(new TemplateData.Builder()
                                                             .type(DataType.SIGNATURE)
                                                             .employee(client.getEmployee(pageTitleDto.getEmployeeId()))
                                                             .build());
            String year = String.valueOf((LocalDate.now().getYear()));
            PageTitleTemplate pageTitle = mapper.mapToUpdatePageTitleTemplate(pageTitleDto);
            HeaderTemplateDto header = headerService.get(pageTitleDto.getReportingDocumentId());
            ReportingDocumentDto reportingDocument = client.getReportingDocument(pageTitleDto.getReportingDocumentId());
            return mapper.mapToPageTitleTemplateDto(repository.save(mapper.mapToPageTitleTemplate(pageTitle
                                                                                                , header
                                                                                               , reportingDocument
                                                                                                , signature
                                                                                                , year)));
        }
        throw new NotFoundException(String.format("PageTitleTemplate with id=%s not found", pageTitleDto.getId()));
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

    private List<PageTitleTemplate> getAllByReportingDocumentIdAndObjectTypeIds(Long reportingDocumentId
                                                                              , List<Long> objectTypeIds) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QPageTitleTemplate.pageTitleTemplate.reportingDocumentId.eq(reportingDocumentId));
        booleanBuilder.and(QPageTitleTemplate.pageTitleTemplate.objectTypeId.in(objectTypeIds));
        QPageTitleTemplate pageTitle = QPageTitleTemplate.pageTitleTemplate;
        return new JPAQueryFactory(entityManager).from(pageTitle)
                                                 .select(pageTitle)
                                                 .where(booleanBuilder)
                                                 .fetch();
    }
}