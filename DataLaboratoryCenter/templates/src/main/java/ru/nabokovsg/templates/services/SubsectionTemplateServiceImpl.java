package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.UpdateSubsectionTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.SubsectionTemplateMapper;
import ru.nabokovsg.templates.models.SubsectionTemplate;
import ru.nabokovsg.templates.models.TableTemplate;
import ru.nabokovsg.templates.repository.SubsectionTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubsectionTemplateServiceImpl implements SubsectionTemplateService {

    private final SubsectionTemplateRepository repository;
    private final SubsectionTemplateMapper mapper;
    private final SubsectionDataTemplateService subsectionDataService;
    private final SectionTemplateService sectionService;
    private final ProtocolTemplateService protocolService;

    @Override
    public SubsectionTemplateDto saveFromSectionTemplate(Long sectionId, NewSubsectionTemplateDto subsectionDto) {
        SubsectionTemplate subsection = repository.findBySubsectionNameAndSectionId(subsectionDto.getSubsectionName(), sectionId);
        if (subsection == null) {
             subsection = repository.save(set(mapper.mapToNewSubsectionTemplate(subsectionDto), subsectionDto));
            sectionService.saveWithSubsectionTemplate(sectionId, subsection);
        }
        return mapper.mapToSubsectionTemplateDto(subsection);
    }

    @Override
    public SubsectionTemplateDto saveFromProtocolTemplate(Long protocolId, NewSubsectionTemplateDto subsectionDto) {
        SubsectionTemplate subsection = repository.findBySubsectionNameAndProtocolId(subsectionDto.getSubsectionName(), protocolId);
        if (subsection == null) {
            subsection = repository.save(set(mapper.mapToNewSubsectionTemplate(subsectionDto), subsectionDto));
            sectionService.saveWithSubsectionTemplate(protocolId, subsection);
            return mapper.mapToSubsectionTemplateDto(subsection);
        }
        return mapper.mapToSubsectionTemplateDto(subsection);
    }

    @Override
    public SubsectionTemplateDto update(UpdateSubsectionTemplateDto subsectionsDto) {
        if (repository.existsById(subsectionsDto.getId())) {
            SubsectionTemplate subsection = mapper.mapToUpdateSubsectionTemplate(subsectionsDto);
            if (!subsectionsDto.getSubsectionDataIds().isEmpty()) {
                subsection.setSubsectionData(subsectionDataService.getAllById(subsectionsDto.getSubsectionDataIds()));
            }
            return mapper.mapToSubsectionTemplateDto(repository.save(subsection));
        }
        throw new NotFoundException(
                String.format("Subsection template with id=%s not found for update", subsectionsDto.getId())
        );
    }

    @Override
    public SubsectionTemplateDto get(Long id) {
        return mapper.mapToSubsectionTemplateDto(getById(id));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("SubsectionTemplate with id=%s not found for delete", id));
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void saveWithTable(Long subsectionId, TableTemplate table) {
        SubsectionTemplate template = getById(subsectionId);
        template.setTable(table);
        repository.save(template);
    }

    private SubsectionTemplate set(SubsectionTemplate subsection, NewSubsectionTemplateDto subsectionDto) {
        if (subsectionDto.getDivision() != null) {
            subsection.setSubsectionData(List.of(subsectionDataService.saveDivisionData((subsectionDto.getDivision()))));
        }
        if (subsectionDto.getDocumentation() != null) {
            subsection.setSubsectionData(subsectionDataService.saveDocumentationData((subsectionDto.getDocumentation())));
        }
        if (subsectionDto.getMeasuringTools() != null) {
            subsection.setSubsectionData(subsectionDataService.saveMeasuringToolData((subsectionDto.getMeasuringTools())));
        }
        return subsection;
    }


    public SubsectionTemplate getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Subsection template with id=%s not found", id))
                );
    }
}