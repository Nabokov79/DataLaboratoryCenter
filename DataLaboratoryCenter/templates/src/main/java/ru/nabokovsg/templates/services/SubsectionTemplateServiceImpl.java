package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.subsection.*;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.SubsectionTemplateMapper;
import ru.nabokovsg.templates.models.SubsectionTemplate;
import ru.nabokovsg.templates.models.SubsectionTemplateData;
import ru.nabokovsg.templates.repository.SubsectionTemplateRepository;
import ru.nabokovsg.templates.services.converter.ConverterStringToEnumService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubsectionTemplateServiceImpl implements SubsectionTemplateService {

    private final SubsectionTemplateRepository repository;
    private final SubsectionTemplateMapper mapper;
    private final TableTemplateService tableService;
    private final ConverterStringToEnumService converter;

    @Override
    public SubsectionTemplateDto save(NewSubsectionTemplateDto subsectionDto) {
        SubsectionTemplate subsection = repository.findBySubsectionDataType(
                                            converter.convertToSubsectionDataType(subsectionDto.getSubsectionDataType())
                                            );
        if (subsection == null) {
             subsection = mapper.mapToNewSubsectionTemplate(subsectionDto);
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
       throw new NotFoundException(
                           String.format("SubsectionTemplate with id=%s not found for update", subsectionsDto.getId()));
    }
    @Override
    public void addSubsectionTemplateData(Long id, List<SubsectionTemplateData> subsectionTemplateData) {
        SubsectionTemplate subsection = get(id);
        subsection.setSubsectionData(subsectionTemplateData);
        repository.save(subsection);
    }

    @Override
    public SubsectionTemplateDto addTableData(Long id, Long tableId) {
        SubsectionTemplate subsection = get(id);
        subsection.setTable(tableService.getById(tableId));
        return mapper.mapToSubsectionTemplateDto(repository.save(subsection));
    }

    @Override
    public List<SubsectionTemplate> getAllById(List<Long> id) {
        List<SubsectionTemplate> subsections = repository.findAllById(id);
        if (subsections.isEmpty()) {
            throw new NotFoundException(String.format("Subsection template with ids=%s not found", id));
        }
        return subsections;
    }

    private SubsectionTemplate get(Long id) {
        return repository.findById(id)
               .orElseThrow(() -> new NotFoundException(String.format("Subsection template with id=%s not found", id)));
    }
}