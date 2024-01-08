package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.templates.dto.subsectionDada.MeasuringToolDataDto;
import ru.nabokovsg.templates.models.MeasuringToolTemplate;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MeasuringToolDataMapper {

    List<MeasuringToolTemplate> mapToMeasuringToolTemplate(List<MeasuringToolDataDto> measuringToolDataDto);
}