package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.nabokovsg.templates.dto.report.ReportTemplateDto;
import ru.nabokovsg.templates.mappers.ReportTemplateMapper;
import ru.nabokovsg.templates.models.PageTitleTemplate;
import ru.nabokovsg.templates.models.ReportTemplate;
import ru.nabokovsg.templates.repository.ReportTemplateRepository;

@Service
@RequiredArgsConstructor
public class ReportTemplateServiceImpl implements ReportTemplateService {

    private final ReportTemplateRepository repository;
    private final ReportTemplateMapper mapper;

    @Override
    public void create(PageTitleTemplate pageTitle, Long reportingDocumentId, Long objectsTypeId) {
        repository.save(mapper.mapToNewReportTemplate(pageTitle, reportingDocumentId, objectsTypeId));
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
    public ReportTemplate getById(Long id) {
        return repository.findById(id)
                    .orElseThrow(() -> new NotFoundException(String.format("ReportTemplate with id=%s not found", id)));
    }

    @Override
    public ReportTemplate getByPageTitle(Long id) {
        ReportTemplate report = repository.findByPageTitleId(id);
        if (report == null) {
            throw new NotFoundException(String.format("ReportTemplate by pageTitleId=%s not found", id));
        }
        return report;
    }
}