package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.tables.NewTableTemplateDto;
import ru.nabokovsg.templates.dto.tables.ShortTableTemplateDto;
import ru.nabokovsg.templates.dto.tables.TableTemplateDto;
import ru.nabokovsg.templates.dto.tables.UpdateTableTemplateDto;
import ru.nabokovsg.templates.exceptions.BadRequestException;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.TableTemplateMapper;
import ru.nabokovsg.templates.models.TableTemplate;
import ru.nabokovsg.templates.models.enums.TableDataType;
import ru.nabokovsg.templates.repository.TableTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableTemplateServiceImpl implements TableTemplateService {

    private final TableTemplateRepository repository;
    private final TableTemplateMapper mapper;
    private final ColumnHeaderTemplateService columnHeaderService;

    @Override
    public TableTemplateDto save(NewTableTemplateDto tableDto) {
        TableTemplate table = repository.findByTableDataType(convertTableDataType(tableDto.getTableDataType()));
        if (table == null) {
            table = mapper.mapToNewTableTemplate(tableDto);
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

    private TableDataType convertTableDataType(String tableDataType) {
        return TableDataType.from(tableDataType)
                .orElseThrow(() -> new BadRequestException(String.format("Unknown table data type=%s", tableDataType)));
    }
}