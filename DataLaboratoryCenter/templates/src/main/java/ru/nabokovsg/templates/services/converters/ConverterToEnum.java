package ru.nabokovsg.templates.services.converters;

import org.springframework.stereotype.Component;
import ru.nabokovsg.templates.exceptions.BadRequestException;
import ru.nabokovsg.templates.models.enums.SubsectionDataType;

@Component
public class ConverterToEnum {

    public SubsectionDataType convertToSubsectionDataType(String subsectionDataType) {
        return SubsectionDataType.from(subsectionDataType)
                .orElseThrow(() -> new BadRequestException(
                        String.format("Unknown subsection data type=%s",subsectionDataType))
                );
    }
}
