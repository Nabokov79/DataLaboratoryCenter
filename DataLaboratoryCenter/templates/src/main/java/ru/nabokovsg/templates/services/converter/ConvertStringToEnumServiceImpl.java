package ru.nabokovsg.templates.services.converter;

import org.springframework.stereotype.Component;

import ru.nabokovsg.templates.exceptions.BadRequestException;
import ru.nabokovsg.templates.models.enums.ColumnDataType;
import ru.nabokovsg.templates.models.enums.ProtocolType;
import ru.nabokovsg.templates.models.enums.SubsectionDataType;
import ru.nabokovsg.templates.models.enums.TableDataType;

@Component
public class ConvertStringToEnumServiceImpl implements ConverterStringToEnumService {

    @Override
    public ColumnDataType covertToColumnDataType(String columnDataType) {
        return ColumnDataType.from(columnDataType)
              .orElseThrow(() -> new BadRequestException(String.format("Unknown column data type=%s", columnDataType)));
    }

    @Override
    public SubsectionDataType convertToSubsectionDataType(String subsectionDataType) {
        return SubsectionDataType.from(subsectionDataType)
                .orElseThrow(() -> new BadRequestException(
                        String.format("Unknown subsection data type=%s",subsectionDataType))
                );
    }

    @Override
    public TableDataType convertToTableDataType(String tableDataType) {
        return TableDataType.from(tableDataType)
                .orElseThrow(() -> new BadRequestException(String.format("Unknown table data type=%s", tableDataType)));
    }

    @Override
    public ProtocolType convertToProtocolType(String protocolType) {
        return ProtocolType.from(protocolType)
                .orElseThrow(() -> new BadRequestException(String.format("Unknown protocol type=%s", protocolType)));
    }
}