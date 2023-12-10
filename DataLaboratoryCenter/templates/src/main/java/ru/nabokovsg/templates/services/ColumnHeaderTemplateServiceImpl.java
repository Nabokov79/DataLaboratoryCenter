package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.columns.NewColumnHeaderTemplateDto;
import ru.nabokovsg.templates.dto.columns.UpdateColumnHeaderTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.ColumnHeaderTemplateMapper;
import ru.nabokovsg.templates.models.ColumnHeaderTemplate;
import ru.nabokovsg.templates.repository.ColumnHeaderTemplateRepository;
import ru.nabokovsg.templates.services.converter.ConverterStringToEnumService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ColumnHeaderTemplateServiceImpl implements ColumnHeaderTemplateService {

    private final ColumnHeaderTemplateRepository repository;
    private final ColumnHeaderTemplateMapper mapper;
    private final ConverterStringToEnumService converter;

    @Override
    public List<ColumnHeaderTemplate> save(List<NewColumnHeaderTemplateDto> templatesDto) {
        Map<String, ColumnHeaderTemplate> columnHeadersDb = repository.findAllByCellName(templatesDto
                        .stream()
                        .map(NewColumnHeaderTemplateDto::getHeading)
                        .toList())
                .stream()
                .collect(Collectors.toMap(ColumnHeaderTemplate::getHeading, c -> c));
        templatesDto = templatesDto.stream()
                .filter(s -> !columnHeadersDb.containsKey(s.getHeading()))
                .toList();
        if (templatesDto.isEmpty()) {
            return columnHeadersDb.values().stream().toList();
        }
        return Stream.of(columnHeadersDb.values()
                        , repository.saveAll(templatesDto
                                .stream()
                                .map(t -> {
                                    ColumnHeaderTemplate template = mapper.mapToNewColumnHeaderTemplates(t);
                                    template.setColumnDataType(converter.covertToColumnDataType(t.getColumnDataType()));
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