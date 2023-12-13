package ru.nabokovsg.templates.services.converter;

import ru.nabokovsg.templates.models.enums.ColumnDataType;
import ru.nabokovsg.templates.models.enums.DivisionType;
import ru.nabokovsg.templates.models.enums.TableDataType;

public interface ConverterStringToEnumService {

    ColumnDataType covertToColumnDataType(String columnDataType);

    DivisionType convertToDivisionType(String divisionType);

    TableDataType convertToTableDataType(String tableDataType);
}