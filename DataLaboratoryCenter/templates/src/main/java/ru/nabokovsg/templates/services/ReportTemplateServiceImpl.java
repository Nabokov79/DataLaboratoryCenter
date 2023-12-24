package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.pageTitle.ShortPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.section.ShortSectionTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.ReportTemplateMapper;
import ru.nabokovsg.templates.models.PageTitleTemplate;
import ru.nabokovsg.templates.models.ReportTemplate;
import ru.nabokovsg.templates.models.SectionTemplate;
import ru.nabokovsg.templates.repository.ReportTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportTemplateServiceImpl implements ReportTemplateService {

    private final ReportTemplateRepository repository;
    private final ReportTemplateMapper mapper;

    @Override
    public List<ShortPageTitleTemplateDto> getAll() {
        return repository.findAllPageTitle().stream().map(mapper::mapToShortPageTitleTemplateDto).toList();
    }

    @Override
    public List<ShortSectionTemplateDto> getAllSections(Long id) {
        return repository.findAllSections(id).stream().map(mapper::mapToShortSectionTemplateDto).toList();
    }

    @Override
    public void saveWithPageTitleTemplate(PageTitleTemplate pageTitle) {
        repository.save(mapper.mapToNewReportTemplate(pageTitle));
    }

    @Override
    public void saveWithSectionTemplate(Long reportId, List<SectionTemplate> sections) {
        repository.save(mapper.mapForSectionTemplate(getById(reportId), sections));
    }

    private ReportTemplate getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Report template with id=%s not found", id))
                );
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}