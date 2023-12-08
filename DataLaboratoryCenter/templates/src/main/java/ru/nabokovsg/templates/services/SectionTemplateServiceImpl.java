package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.sections.*;
import ru.nabokovsg.templates.exceptions.NotFoundException;
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

    @Override
    public List<SectionTemplateDto> save(NewSectionTemplateDto sectionsDto) {
        Map<String, SectionTemplate> sectionsDb =
                repository.findByObjectTypeIdAndReportingDocumentId(sectionsDto.getReportingDocumentId()
                                                          , sectionsDto.getObjectTypeId())
                                                    .stream()
                                                    .collect(Collectors.toMap(SectionTemplate::getSectionName, s -> s));
        List<NewSectionTemplateDataDto> sectionsData = sectionsDto.getSectionsData()
                                                               .stream()
                                                               .filter(d -> !sectionsDb.containsKey(d.getSectionName()))
                                                               .toList();
        if (sectionsData.isEmpty()) {
            return map(sectionsDb.values().stream().toList());
        }
        return map(repository.saveAll(sectionsDto.getSectionsData()
                .stream()
                .map(d -> mapper.mapToNewSectionTemplate(sectionsDto.getObjectTypeId()
                                                       , sectionsDto.getReportingDocumentId(), d))
                .toList()));

    }

    @Override
    public List<SectionTemplateDto> update(List<UpdateSectionTemplateDto> sectionsDto) {
        List<Long> ids = sectionsDto.stream().map(UpdateSectionTemplateDto::getId).toList();
        Map<Long, SectionTemplate> sectionDb = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(SectionTemplate::getId, m -> m));
        validateIds(sectionDb, ids);
        return map(repository.saveAll(sectionsDto.stream()
                                               .map(s -> mapper.mapToUpdateSectionTemplate(sectionDb.get(s.getId()), s))
                                               .toList()));
    }

    @Override
    public List<SectionTemplateDto> getAll(Long objectTypeId, Long reportingDocumentId) {
        return map(repository.findByObjectTypeIdAndReportingDocumentId(objectTypeId, reportingDocumentId).stream()
                                                                                                         .toList());
    }

    @Override
    public Set<SectionTemplate> create(Long objectTypeId, Long reportingDocumentId) {
        return new HashSet<>(repository.findByObjectTypeIdAndReportingDocumentId(objectTypeId, reportingDocumentId));
    }

    private List<SectionTemplateDto> map(List<SectionTemplate> sections) {
        return sections.stream()
                        .map(mapper::mapToSectionTemplateDto)
                        .toList();
    }

    private void validateIds(Map<Long, SectionTemplate> sectionTemplates, List<Long> ids) {
        if (sectionTemplates.size() != ids.size() || sectionTemplates.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(sectionTemplates.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("Section templates with ids= %s not found", ids));
        }
    }
}