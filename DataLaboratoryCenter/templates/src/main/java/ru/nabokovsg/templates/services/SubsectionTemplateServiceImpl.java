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
        if (sectionService.existsById(sectionId)) {
            SubsectionTemplate subsection = mapper.mapToNewSubsectionTemplate(subsectionDto);
            if (!subsectionDto.getSubsectionDataIds().isEmpty()) {
                subsection.setSubsectionData(subsectionDataService.getAllById(subsectionDto.getSubsectionDataIds()));
            }
            subsection = repository.save(subsection);
            sectionService.saveWithSubsectionTemplate(sectionId, subsection);
            return mapper.mapToSubsectionTemplateDto(subsection);
        }
        throw new NotFoundException(
                String.format("Section template with id=%s not found for add subsection template", sectionId)
        );
    }

    @Override
    public SubsectionTemplateDto saveFromProtocolTemplate(Long protocolId, NewSubsectionTemplateDto subsectionDto) {
        if (protocolService.existsById(protocolId)) {
            SubsectionTemplate subsection = mapper.mapToNewSubsectionTemplate(subsectionDto);
            if (!subsectionDto.getSubsectionDataIds().isEmpty()) {
                subsection.setSubsectionData(subsectionDataService.getAllById(subsectionDto.getSubsectionDataIds()));
            }
            return mapper.mapToSubsectionTemplateDto(repository.save(subsection));
        }
        throw new NotFoundException(
                String.format("Protocol template with id=%s not found for add subsection template", protocolId)
        );
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

    public SubsectionTemplate getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Subsection template with id=%s not found", id))
                );
    }
}