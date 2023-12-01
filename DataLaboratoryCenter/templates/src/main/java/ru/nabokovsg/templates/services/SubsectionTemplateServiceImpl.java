package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.subsection.*;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.SubsectionTemplateMapper;
import ru.nabokovsg.templates.models.SubsectionTemplate;
import ru.nabokovsg.templates.models.SubsectionTemplateData;
import ru.nabokovsg.templates.repository.SubsectionTemplateRepository;
import ru.nabokovsg.templates.services.converters.ConverterToEnum;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SubsectionTemplateServiceImpl implements SubsectionTemplateService {

    private final SubsectionTemplateRepository repository;
    private final SubsectionTemplateMapper mapper;
    private final ConverterToEnum converter;
    private final TableTemplateService tableService;

    @Override
    public List<SubsectionTemplateDto> save(List<NewSubsectionTemplateDto> subsectionsDto) {
        Set<SubsectionTemplate> subsectionsDb = repository.findAllBySectionIdAndSubsectionDataType(
                                    subsectionsDto.stream()
                                                  .map(NewSubsectionTemplateDto::getSectionId)
                                                  .distinct()
                                                  .toList()
                                  , subsectionsDto.stream()
                                .map(t -> converter.convertToSubsectionDataType(t.getSubsectionDataType()))
                                                    .toList());
        if (subsectionsDb.isEmpty()) {
            List<SubsectionTemplate> subsections = mapper.mapToNewSubsectionTemplate(subsectionsDto);
            return mapper.mapToSubsectionTemplateDto(repository.saveAll(subsections));
        }
        List<SubsectionTemplate> subsections = repository.saveAll(mapper.mapToNewSubsectionTemplate(
                filter(subsectionsDb.stream()
                                .map(SubsectionTemplate::getSubsectionDataType)
                                .map(String::valueOf)
                                .collect(Collectors.toSet())
                        , subsectionsDto)));
        return mapper.mapToSubsectionTemplateDto(Stream.of(subsectionsDb, subsections)
                                                       .flatMap(Collection::stream)
                                                       .toList());
    }

    @Override
    public List<SubsectionTemplateDto> update(UpdateSubsectionTemplateDto subsectionsDto) {
       if (!repository.existsById(subsectionsDto.getId())) {
           SubsectionTemplate subsections = mapper.mapToUpdateSubsectionTemplate(subsectionsDto);
           return mapper.mapToSubsectionTemplateDto(List.of(repository.save(subsections)));
       }
       throw new NotFoundException(String.format("SubsectionTemplate with id=%s not found for update", subsectionsDto.getId()));
    }

    @Override
    public List<SubsectionTemplateDto> getAll(Long sectionId) {
        return mapper.mapToSubsectionTemplateDto(repository.findAllBySectionId(sectionId).stream().toList());
    }

    @Override
    public void addSubsectionTemplateData(Long subsectionId, List<SubsectionTemplateData> subsectionTemplateData) {
        SubsectionTemplate subsection = get(subsectionId);
        subsection.setSubsectionData(subsectionTemplateData);
        repository.save(subsection);
    }

    private SubsectionTemplate get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Subsection template with id=%s not found", id)));
    }


    private List<NewSubsectionTemplateDto> filter(Set<String> subsectionDataType, List<NewSubsectionTemplateDto> subsectionsDto) {
        return subsectionsDto.stream().filter(s -> !subsectionDataType.contains(s.getSubsectionDataType())).toList();
    }
}