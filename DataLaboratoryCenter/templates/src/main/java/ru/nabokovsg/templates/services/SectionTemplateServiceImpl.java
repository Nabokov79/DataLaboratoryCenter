package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.section.NewSectionTemplateDto;
import ru.nabokovsg.templates.dto.section.SectionTemplateDto;
import ru.nabokovsg.templates.dto.section.UpdateSectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.ShortSubsectionTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.SectionTemplateMapper;
import ru.nabokovsg.templates.models.SectionTemplate;
import ru.nabokovsg.templates.models.SubsectionTemplate;
import ru.nabokovsg.templates.repository.SectionTemplateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectionTemplateServiceImpl implements SectionTemplateService {

    private final SectionTemplateRepository repository;
    private final SectionTemplateMapper mapper;
    private final ReportTemplateService reportService;

    @Override
    public List<SectionTemplateDto> save(Long reportId, List<NewSectionTemplateDto> sectionsDto) {
        if (reportService.existsById(reportId)) {
            List<SectionTemplate> sections = repository.saveAll(sectionsDto.stream()
                    .map(mapper::mapToNewSectionTemplate)
                    .toList());
            reportService.saveWithSectionTemplate(reportId, sections);
            return sections.stream()
                    .map(mapper::mapToSectionTemplateDto)
                    .toList();
        }
        throw new NotFoundException(
                String.format("Report template with id=%s not found for add section template", reportId)
        );
    }

    @Override
    public List<SectionTemplateDto> update(List<UpdateSectionTemplateDto> sectionsDto) {
        validateIds(sectionsDto.stream().map(UpdateSectionTemplateDto::getId).toList());
        return repository.saveAll(sectionsDto.stream()
                                             .map(mapper::mapToUpdateSectionTemplate)
                                             .toList())
                                             .stream()
                                             .map(mapper::mapToSectionTemplateDto)
                                             .toList();
    }

    @Override
    public SectionTemplateDto get(Long id) {
        return mapper.mapToSectionTemplateDto(getById(id));
    }

    @Override
    public List<ShortSubsectionTemplateDto> getAllSubsections(Long id) {
        return repository.findAllSubsection(id).stream().map(mapper::mapToShortSubsectionTemplateDto).toList();
    }

    @Override
    public void saveWithSubsectionTemplate(Long sectionId, SubsectionTemplate subsection) {
        SectionTemplate section = getById(sectionId);
        section.getSubsections().add(subsection);
        repository.save(section);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    private SectionTemplate getById(Long id) {
        return repository.findById(id).orElseThrow(
                        () -> new NotFoundException(
                                String.format(String.format("Section template with id= %s not found", id))
                                    )
                                );
    }

    private void validateIds(List<Long> ids) {
        Map<Long, SectionTemplate> sections = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(SectionTemplate::getId, m -> m));
        if (sections.size() != ids.size() || sections.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(sections.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("Section template with ids= %s not found", ids));
        }
    }
}
