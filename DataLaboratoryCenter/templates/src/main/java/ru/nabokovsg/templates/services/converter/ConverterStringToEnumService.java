package ru.nabokovsg.templates.services.converter;

import ru.nabokovsg.templates.models.enums.ColumnDataType;
import ru.nabokovsg.templates.models.enums.ProtocolType;
import ru.nabokovsg.templates.models.enums.SubsectionDataType;
import ru.nabokovsg.templates.models.enums.TableDataType;

public interface ConverterStringToEnumService {

    ColumnDataType covertToColumnDataType(String columnDataType);

    SubsectionDataType convertToSubsectionDataType(String subsectionDataType);

    TableDataType convertToTableDataType(String tableDataType);

    ProtocolType convertToProtocolType(String protocolType);
}