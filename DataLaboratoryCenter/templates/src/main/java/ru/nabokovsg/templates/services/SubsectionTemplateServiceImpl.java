package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.subsection.*;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.SubsectionTemplateMapper;
import ru.nabokovsg.templates.models.SubsectionTemplateData;
import ru.nabokovsg.templates.models.SubsectionTemplate;
import ru.nabokovsg.templates.repository.SubsectionTemplateRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubsectionTemplateServiceImpl implements SubsectionTemplateService {

    private final SubsectionTemplateRepository repository;
    private final SubsectionTemplateMapper mapper;
    private final SubsectionTemplateDataService subsectionDataService;

    @Override
    public List<SubsectionTemplateDto> save(List<NewSubsectionTemplateDto> subsectionsDto) {
        Set<SubsectionTemplate> subsectionsDb = repository.findAllBySectionIdAndSubsectionDataType(
                                    subsectionsDto.stream()
                                                  .map(NewSubsectionTemplateDto::getSectionId)
                                                  .distinct()
                                                  .toList()
                                  , subsectionsDto.stream()
                                                    .map(NewSubsectionTemplateDto::getSubsectionDataType)
                                                    .toList());
        if (subsectionsDb.size() != subsectionsDto.size()) {
            if (!subsectionsDb.isEmpty()) {
                subsectionsDto = filter(subsectionsDb.stream()
                                                     .map(SubsectionTemplate::getSubsectionDataType)
                                                     .map(String::valueOf).collect(Collectors.toSet())
                                      , subsectionsDto);
            }
            List<SubsectionTemplateData> data = subsectionDataService.save(subsectionsDto.stream()
                    .map(NewSubsectionTemplateDto::getSubsectionsData)
                    .toList());
            List<SubsectionTemplate> subsections = mapper.mapToNewSubsectionTemplate(
                            subsectionsDto).stream()
                    .peek(s -> s.setSubsectionData(
                                    data.stream()
                                        .filter(d -> d.getSubsectionDataType().equals(s.getSubsectionDataType().name()))
                                        .toList()))
                    .toList();
            subsectionsDb.addAll(repository.saveAll(subsections));
        }
        return mapper.mapToSubsectionTemplateDto(subsectionsDb.stream().toList());
    }

    @Override
    public List<SubsectionTemplateDto> update(UpdateSubsectionTemplateDto subsectionsDto) {
       if (!repository.existsById(subsectionsDto.getId())) {
           SubsectionTemplate subsections = mapper.mapToUpdateSubsectionTemplate(subsectionsDto);
           subsections.setSubsectionData(subsectionDataService.update(subsectionsDto.getSubsectionsData()));
           return mapper.mapToSubsectionTemplateDto(List.of(repository.save(subsections)));
       }
       throw new NotFoundException(String.format("SubsectionTemplate with id=%s not found for update", subsectionsDto.getId()));
    }

    @Override
    public Set<SubsectionTemplate> getAllForSectionTemplate(Long sectionId) {
        return repository.findAllBySectionId(sectionId);
    }

    private List<NewSubsectionTemplateDto> filter(Set<String> subsectionDataType, List<NewSubsectionTemplateDto> subsectionsDto) {
        return subsectionsDto.stream().filter(s -> !subsectionDataType.contains(s.getSubsectionDataType())).toList();
    }
}