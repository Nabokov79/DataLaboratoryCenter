package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.sections.SectionTemplateDto;
import ru.nabokovsg.templates.dto.sections.NewSectionTemplateDto;
import ru.nabokovsg.templates.dto.sections.UpdateSectionTemplateDataDto;
import ru.nabokovsg.templates.dto.sections.UpdateSectionTemplateDto;
import ru.nabokovsg.templates.exceptions.BadRequestException;
import ru.nabokovsg.templates.mappers.SectionTemplateMapper;
import ru.nabokovsg.templates.models.SectionTemplate;
import ru.nabokovsg.templates.repository.SectionTemplateRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SectionTemplateServiceImpl implements SectionTemplateService {

    private final SectionTemplateRepository repository;
    private final SectionTemplateMapper mapper;
    private final SubsectionTemplateService subsectionTemplateService;

    @Override
    public List<SectionTemplateDto> save(NewSectionTemplateDto sectionsDto) {
        List<SectionTemplate> sections = new ArrayList<>();
        for (Long id : sectionsDto.getReportingDocumentIds()) {
            sections.addAll(mapper.mapToNewSectionTemplate(sectionsDto.getSectionsData()).stream()
                    .peek(s -> s.setReportingDocumentId(id))
                    .toList());
        }
        try {
            sections = repository.saveAll(sections);
        } catch (DataIntegrityViolationException e) {
            log.error(String.format("Duplicate section template massage=%s", e.getMessage()));
        }
        return map(new HashSet<>(sections));
    }

    @Override
    public List<SectionTemplateDto> update(UpdateSectionTemplateDto sectionsDto) {
        Map<Long, UpdateSectionTemplateDataDto> sectionsData = sectionsDto.getSectionsData()
                                            .stream()
                                            .collect(Collectors.toMap(UpdateSectionTemplateDataDto::getId, d -> d));
        Set<SectionTemplate> sections = repository.findAllByReportingDocumentId(sectionsDto.getReportingDocumentId());
        if (sections.isEmpty()) {
            throw new BadRequestException(
                    String.format("Section Template by reportingDocumentId=%s not found for update "
                                                                               , sectionsDto.getReportingDocumentId()));
        }
        return map(new HashSet<>(repository.saveAll(sections.stream()
                .map(s -> {
                    UpdateSectionTemplateDataDto data = sectionsData.get(s.getId());
                    if (data != null) {
                        SectionTemplate template = mapper.mapToUpdateSectionTemplate(data);
                        template.setReportingDocumentId(s.getReportingDocumentId());
                        return template;
                    }
                    return s;
                }).toList())));
    }

    @Override
    public List<SectionTemplateDto> get(Long reportingDocumentId) {
        return map(repository.findAllByReportingDocumentId(reportingDocumentId));
    }

    @Override
    public Set<SectionTemplate> create(Long reportingDocumentId) {
        return repository.findAllByReportingDocumentId(reportingDocumentId)
                .stream()
                .peek(s -> s.setSubsections(subsectionTemplateService.getAllForSectionTemplate(s.getId())))
                .collect(Collectors.toSet());
    }

    private List<SectionTemplateDto> map(Set<SectionTemplate> sections) {
        return sections.stream()
                        .map(mapper::mapToSectionTemplateDto)
                        .toList();
    }
}