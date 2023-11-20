package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.sizeParameters.NewSizeParametersDto;
import ru.nabokovsg.data.dto.sizeParameters.UpdateSizeParametersDto;
import ru.nabokovsg.data.models.SizeParameters;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DefectParameterMapper {

    List<SizeParameters> mapToNewDefectParameter(List<NewSizeParametersDto> parametersDto);

    List<SizeParameters> mapToUpdateDefectParameter(List<UpdateSizeParametersDto> parametersDto);
}