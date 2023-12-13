package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.UpdateSubsectionTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.SubsectionTemplateMapper;
import ru.nabokovsg.templates.models.SubsectionTemplate;
import ru.nabokovsg.templates.repository.SubsectionTemplateRepository;

@Service
@RequiredArgsConstructor
public class SubsectionTemplateServiceImpl implements SubsectionTemplateService {

    private final SubsectionTemplateRepository repository;
    private final SubsectionTemplateMapper mapper;
    private final  TableTemplateService tableService;
    private final SubsectionDataTemplateService subsectionDataService;

    @Override
    public SubsectionTemplateDto save(NewSubsectionTemplateDto subsectionsDto) {
        SubsectionTemplate subsection = mapper.mapToNewSubsectionTemplate(subsectionsDto);
        if (subsectionsDto.getTableDataType() != null) {
            subsection.setTable(tableService.getByTableDataType(subsectionsDto.getTableDataType()));

        }
        if (!subsectionsDto.getSubsectionDataIds().isEmpty()) {
            subsection.setSubsectionData(subsectionDataService.getAllById(subsectionsDto.getSubsectionDataIds()));
        }
        return mapper.mapToSubsectionTemplateDto(repository.save(subsection));
    }

    @Override
    public SubsectionTemplateDto update(UpdateSubsectionTemplateDto subsectionsDto) {
        if (repository.existsById(subsectionsDto.getId())) {
            SubsectionTemplate subsection = mapper.mapToUpdateSubsectionTemplate(subsectionsDto);
            if (subsectionsDto.getTableDataType() != null) {
                subsection.setTable(tableService.getByTableDataType(subsectionsDto.getTableDataType()));

            }
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
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("SubsectionTemplate with id=%s not found for delete", id));
    }
}