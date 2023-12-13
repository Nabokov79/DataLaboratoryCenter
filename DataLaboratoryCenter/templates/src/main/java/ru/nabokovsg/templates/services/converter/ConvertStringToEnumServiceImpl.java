package ru.nabokovsg.templates.services.converter;

import org.springframework.stereotype.Component;

import ru.nabokovsg.templates.exceptions.BadRequestException;
import ru.nabokovsg.templates.models.enums.ColumnDataType;
import ru.nabokovsg.templates.models.enums.DivisionType;
import ru.nabokovsg.templates.models.enums.TableDataType;

@Component
public class ConvertStringToEnumServiceImpl implements ConverterStringToEnumService {

    @Override
    public ColumnDataType covertToColumnDataType(String columnDataType) {
        return ColumnDataType.from(columnDataType)
              .orElseThrow(() -> new BadRequestException(String.format("Unknown column data type=%s", columnDataType)));
    }

    @Override
    public DivisionType convertToDivisionType(String divisionType) {
        return DivisionType.from(divisionType)
                .orElseThrow(() -> new BadRequestException(String.format("Unknown division type=%s", divisionType)));
    }

    @Override
    public TableDataType convertToTableDataType(String tableDataType) {
        return TableDataType.from(tableDataType)
                .orElseThrow(() -> new BadRequestException(String.format("Unknown table data type=%s", tableDataType)));
    }
}