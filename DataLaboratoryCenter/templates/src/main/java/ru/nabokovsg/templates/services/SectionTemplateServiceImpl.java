package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.report.ReportTemplateDto;
import ru.nabokovsg.templates.dto.sections.NewReportSectionTemplateDto;
import ru.nabokovsg.templates.dto.sections.NewSectionTemplateDto;
import ru.nabokovsg.templates.mappers.SectionTemplateMapper;
import ru.nabokovsg.templates.models.ReportTemplate;
import ru.nabokovsg.templates.models.SectionTemplate;
import ru.nabokovsg.templates.repository.SectionTemplateRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectionTemplateServiceImpl implements SectionTemplateService {

    private final SectionTemplateRepository repository;
    private final SectionTemplateMapper mapper;
    private final ReportTemplateService service;

    @Override
    public ReportTemplateDto save(NewReportSectionTemplateDto sectionTemplateDto) {
        ReportTemplate report = service.getByPageTitle(sectionTemplateDto.getPageTitleId());
        List<NewSectionTemplateDto> sections = sectionTemplateDto.getSections();
        if (report != null) {
            if (report.getSections() != null) {
                Set<String> sectionNames = report.getSections()
                                                               .stream()
                                                               .map(SectionTemplate::getSectionName)
                                                               .collect(Collectors.toSet());
                sections = sections.stream()
                                   .filter(s -> !sectionNames.contains(s.getSectionName()))
                                   .toList();
            }
            if (!sections.isEmpty()) {
                report.setSections(repository.saveAll(sections.stream()
                                                              .map(mapper::mapToNewSectionTemplate)
                                                              .toList()));
            }
        }
        return service.save(report);
    }
}