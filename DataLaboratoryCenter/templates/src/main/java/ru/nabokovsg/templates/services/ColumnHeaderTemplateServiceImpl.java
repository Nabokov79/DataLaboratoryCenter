package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.tables.columns.NewColumnHeaderTemplateDto;
import ru.nabokovsg.templates.dto.tables.columns.UpdateColumnHeaderTemplateDto;
import ru.nabokovsg.templates.exceptions.BadRequestException;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.ColumnHeaderTemplateMapper;
import ru.nabokovsg.templates.models.ColumnHeaderTemplate;
import ru.nabokovsg.templates.models.enums.ColumnDataType;
import ru.nabokovsg.templates.repository.ColumnHeaderTemplateRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ColumnHeaderTemplateServiceImpl implements ColumnHeaderTemplateService {

    private final ColumnHeaderTemplateRepository repository;
    private final ColumnHeaderTemplateMapper mapper;

    @Override
    public List<ColumnHeaderTemplate> save(List<NewColumnHeaderTemplateDto> templatesDto) {
        Map<String, ColumnHeaderTemplate> columnHeadersDb = repository.findAllByCellName(templatesDto
                                                                           .stream()
                                                                           .map(NewColumnHeaderTemplateDto::getCellName)
                                                                           .toList())
                                                .stream()
                                                .collect(Collectors.toMap(ColumnHeaderTemplate::getCellName, c -> c));
        templatesDto = templatesDto.stream()
                .filter(s -> !columnHeadersDb.containsKey(s.getCellName()))
                .toList();
        if (templatesDto.isEmpty()) {
            return columnHeadersDb.values().stream().toList();
        }
        return Stream.of(columnHeadersDb.values()
                        , repository.saveAll(templatesDto
                                .stream()
                                .map(t -> {
                                    ColumnHeaderTemplate template = mapper.mapToNewColumnHeaderTemplates(t);
                                    template.setColumnDataType(covertColumnDataType(t.getColumnDataType()));
                                    return template;
                                })
                                .toList()))
                .flatMap(Collection::stream)
                .toList();
    }

    @Override
    public List<ColumnHeaderTemplate> update(List<UpdateColumnHeaderTemplateDto> templatesDto) {
        validateIds(templatesDto.stream().map(UpdateColumnHeaderTemplateDto::getId).toList());
        return repository.saveAll(templatesDto.stream().map(mapper::mapToUpdateColumnHeaderTemplates).toList());
    }

    private ColumnDataType covertColumnDataType(String columnDataType) {
        return ColumnDataType.from(columnDataType)
                .orElseThrow(() -> new BadRequestException(String.format("Unknown column data type=%s", columnDataType)));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, ColumnHeaderTemplate> columnHeaderTemplate = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(ColumnHeaderTemplate::getId, m -> m));
        if (columnHeaderTemplate.size() != ids.size() || columnHeaderTemplate.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(columnHeaderTemplate.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("Column header template with ids= %s not found", ids));
        }
    }
}