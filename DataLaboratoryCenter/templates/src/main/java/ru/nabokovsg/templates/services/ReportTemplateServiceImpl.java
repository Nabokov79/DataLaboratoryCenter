package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.nabokovsg.templates.dto.pageTitle.ShortPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.report.ReportTemplateDto;
import ru.nabokovsg.templates.mappers.ReportTemplateMapper;
import ru.nabokovsg.templates.models.PageTitleTemplate;
import ru.nabokovsg.templates.models.ReportTemplate;
import ru.nabokovsg.templates.repository.PageTitleTemplateRepository;
import ru.nabokovsg.templates.repository.ReportTemplateRepository;
import ru.nabokovsg.templates.repository.SectionTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportTemplateServiceImpl implements ReportTemplateService {

    private final ReportTemplateRepository repository;
    private final ReportTemplateMapper mapper;
    private final PageTitleTemplateRepository titleRepository;
    private final SectionTemplateRepository sectionRepository;

    @Override
    public ReportTemplateDto create(Long reportingDocumentId, Long objectsTypeId) {
        return mapper.mapToReportTemplateDto(
                repository.save(mapper.mapToNewReportTemplate(getPageTitleTemplate(reportingDocumentId)
                              , sectionRepository.findAllByReportingDocumentId(reportingDocumentId)
                              , objectsTypeId
                              , reportingDocumentId))
        );
    }

    @Override
    public ReportTemplateDto save(ReportTemplate report) {
        return mapper.mapToReportTemplateDto(repository.save(report));
    }

    @Override
    public ReportTemplateDto get(Long objectTypeId, Long reportingDocumentId) {
        ReportTemplate report = repository.findByObjectTypeIdAndReportingDocumentId(objectTypeId, reportingDocumentId);
        if (report == null) {
            throw new NotFoundException(
                    String.format("ReportTemplate by objectsTypeId=%s, reportingDocumentId=%s not found"
                                                                                        , objectTypeId
                                                                                        , reportingDocumentId));
        }
        return mapper.mapToReportTemplateDto(report);
    }

    @Override
    public List<ShortPageTitleTemplateDto> getAll() {
        return repository.findAll().stream()
                                   .map(r -> mapper.mapToShortPageTitleTemplateDto(r.getId(), r.getPageTitle()))
                                   .toList();
    }

    private PageTitleTemplate getPageTitleTemplate(Long reportingDocumentId) {
        return titleRepository.findByReportingDocumentId(reportingDocumentId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("PageTitleTemplate by reportingDocumentId=%s not found for create ReportTemplate", reportingDocumentId)));
    }
}