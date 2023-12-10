package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.table.NewTableTemplateDto;
import ru.nabokovsg.templates.dto.table.TableTemplateDto;
import ru.nabokovsg.templates.dto.table.UpdateTableTemplateDto;
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
    public TableTemplateDto save(NewTableTemplateDto tableDto) {
        return null;
    }

    @Override
    public TableTemplateDto update(UpdateTableTemplateDto tableDto) {
        return null;
    }
}