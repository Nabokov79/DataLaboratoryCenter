package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.tables.*;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.TableTemplateMapper;
import ru.nabokovsg.templates.models.TableTemplate;
import ru.nabokovsg.templates.repository.TableTemplateRepository;
import ru.nabokovsg.templates.services.converter.ConverterStringToEnumService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableTemplateServiceImpl implements TableTemplateService {

    private final TableTemplateRepository repository;
    private final TableTemplateMapper mapper;
    private final ColumnHeaderTemplateService columnHeaderService;
    private final ConverterStringToEnumService converter;

    @Override
    public TableTemplateDto saveForProtocol(NewProtocolTableTemplateDto tableDto) {
        TableTemplate table = repository.findByProtocolTypeAndTableDataType(
                                                                     converter.convertToProtocolType(tableDto.getProtocolType())
                                                                   , converter.convertToTableDataType(tableDto.getTableDataType()));
        if (table == null) {
            table = mapper.mapToNewProtocolTableTemplate(tableDto);
            table.setColumnHeaders(columnHeaderService.save(tableDto.getColumnHeaders()));
        }
        return mapper.mapToTableTemplateDto(repository.save(table));
    }

    @Override
    public TableTemplateDto saveForSubsection(NewSubsectionTableTemplateDto tableDto) {
        TableTemplate table = repository.findBySubsectionDataType(
                                                 converter.convertToSubsectionDataType(tableDto.getSubsectionDataType())
                                                 );
        if (table == null) {
            table = mapper.mapToNewSubsectionTableTemplate(tableDto);
            table.setColumnHeaders(columnHeaderService.save(tableDto.getColumnHeaders()));
        }
        return mapper.mapToTableTemplateDto(repository.save(table));
    }

    @Override
    public TableTemplateDto update(UpdateTableTemplateDto tableDto) {
        if (repository.existsById(tableDto.getId())) {
            TableTemplate table = mapper.mapToUpdateTableTemplate(tableDto);
            table.setColumnHeaders(columnHeaderService.update(tableDto.getColumnHeaders()));
            return mapper.mapToTableTemplateDto(repository.save(table));
        }
        throw new NotFoundException(String.format("Table template with id=%s not found for update", tableDto.getId()));
    }

    @Override
    public List<ShortTableTemplateDto> getAll() {
        return repository.findAll().stream().map(mapper::mapToShortTableTemplateDto).toList();
    }

    @Override
    public TableTemplate getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Table Template with id=%s not found", id)));
    }

    @Override
    public List<TableTemplate> getAllById(List<Long> ids) {
        List<TableTemplate> tables = repository.findAllById(ids);
        if (tables.isEmpty()) {
            throw new NotFoundException(String.format("Tables Templates by ids=%s not found", ids));
        }
        return tables;
    }
}