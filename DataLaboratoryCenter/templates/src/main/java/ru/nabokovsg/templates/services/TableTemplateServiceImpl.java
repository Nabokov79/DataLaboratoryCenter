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
import ru.nabokovsg.templates.services.converter.ConverterStringToEnumService;

@Service
@RequiredArgsConstructor
public class TableTemplateServiceImpl implements TableTemplateService {

    private final TableTemplateRepository repository;
    private final TableTemplateMapper mapper;
    private final ColumnHeaderTemplateService columnHeaderService;
    private final ConverterStringToEnumService converter;


    @Override
    public TableTemplateDto save(NewTableTemplateDto tableDto) {
        TableTemplate table = repository.findByTableDataTypeAndObjectTypeIdAndReportingDocumentId(
                                                          converter.convertToTableDataType(tableDto.getTableDataType())
                                                        , tableDto.getObjectTypeId()
                                                        , tableDto.getReportingDocumentId());
        if (table == null) {
            table = repository.save(mapper.mapToNewTableTemplate(tableDto
                                                               , columnHeaderService.save(tableDto.getColumnHeaders()))
            );
        }
        return mapper.mapToTableTemplateDto(table);
    }

    @Override
    public TableTemplateDto update(UpdateTableTemplateDto tableDto) {
        return mapper.mapToTableTemplateDto(
                repository.save(
                        mapper.mapToUpdateTableTemplate(get(tableDto.getId())
                                                          , tableDto
                                                          , columnHeaderService.update(tableDto.getColumnHeaders()))
                )
        );
    }

    private TableTemplate get(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Table template with id=%s not found", id))
        );
    }
}