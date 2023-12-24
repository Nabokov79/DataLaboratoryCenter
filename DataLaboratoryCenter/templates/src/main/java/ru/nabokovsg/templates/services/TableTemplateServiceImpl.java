package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.table.NewTableTemplateDto;
import ru.nabokovsg.templates.dto.table.TableTemplateDto;
import ru.nabokovsg.templates.dto.table.UpdateTableTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.TableTemplateMapper;
import ru.nabokovsg.templates.models.TableTemplate;
import ru.nabokovsg.templates.repository.TableTemplateRepository;

@Service
@RequiredArgsConstructor
public class TableTemplateServiceImpl implements TableTemplateService {

    private final TableTemplateRepository repository;
    private final TableTemplateMapper mapper;
    private final ColumnHeaderTemplateService columnHeaderService;
    private final SubsectionTemplateService subsectionService;
    private final ProtocolTemplateService protocolService;


    @Override
    public TableTemplateDto saveFromSubsectionTemplate(Long subsectionId, NewTableTemplateDto tableDto) {
        if (subsectionService.existsById(subsectionId)) {
            TableTemplate table = repository.save(mapper.mapToNewTableTemplate(tableDto
                    , columnHeaderService.save(tableDto.getColumnHeaders())));
            subsectionService.saveWithTable(subsectionId, table);
            return mapper.mapToTableTemplateDto(table);
        }
        throw new NotFoundException(
                String.format("Subsection template with id=%s not found for add table template", subsectionId)
        );
    }

    @Override
    public TableTemplateDto saveFromProtocolTemplate(Long protocolId, NewTableTemplateDto tableDto) {
        if (protocolService.existsById(protocolId)) {
            TableTemplate table = repository.save(mapper.mapToNewTableTemplate(tableDto
                    , columnHeaderService.save(tableDto.getColumnHeaders())));
            protocolService.saveWithTable(protocolId, table);
            return mapper.mapToTableTemplateDto(table);
        }
        throw new NotFoundException(
                String.format("Protocol template with id=%s not found for add table template", protocolId)
        );
    }

    @Override
    public TableTemplateDto update(UpdateTableTemplateDto tableDto) {
        if (repository.existsById(tableDto.getId())) {
            return mapper.mapToTableTemplateDto(repository.save(mapper.mapToUpdateTableTemplate(
                                                                tableDto
                                                              , columnHeaderService.update(tableDto.getColumnHeaders()))
                    )
            );
        }
       throw new NotFoundException(String.format("Table template with id=%s not found for update", tableDto.getId()));
    }

    @Override
    public TableTemplateDto get(Long id) {
        return mapper.mapToTableTemplateDto(getById(id));
    }

    public TableTemplate getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Table template with id=%s not found", id)));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw  new NotFoundException(String.format("Table template with id=%s not found for delete", id));
    }
}