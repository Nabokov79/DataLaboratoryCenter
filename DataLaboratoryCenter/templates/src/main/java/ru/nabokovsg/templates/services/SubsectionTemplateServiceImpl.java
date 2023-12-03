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

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubsectionTemplateServiceImpl implements SubsectionTemplateService {

    private final SubsectionTemplateRepository repository;
    private final SubsectionTemplateMapper mapper;
    private final ConverterToEnum converter;
    private final TableTemplateService tableService;

    @Override
    public SubsectionTemplateDto save(NewSubsectionTemplateDto subsectionDto) {
        SubsectionTemplate subsection = repository.findBySubsectionDataType(subsectionDto.getSubsectionDataType());
        if (subsection == null) {
             subsection = mapper.mapToNewSubsectionTemplate(subsectionDto);
             subsection.setSubsectionData(dataService.save(subsectionDto.getSubsectionDataType(), subsectionDto.getSubsectionData()));
            subsection = repository.save(subsection);
        }
        return mapper.mapToSubsectionTemplateDto(subsection);
    }

    @Override
    public SubsectionTemplateDto update(UpdateSubsectionTemplateDto subsectionsDto) {
       if (!repository.existsById(subsectionsDto.getId())) {
           SubsectionTemplate subsections = mapper.mapToUpdateSubsectionTemplate(subsectionsDto);
           return mapper.mapToSubsectionTemplateDto(repository.save(subsections));
       }
       throw new NotFoundException(String.format("SubsectionTemplate with id=%s not found for update", subsectionsDto.getId()));
    }

    @Override
    public List<SubsectionTemplateDto> getAll(Long sectionId) {
        return repository.findAllBySectionId(sectionId).stream().map(mapper::mapToSubsectionTemplateDto).toList();
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