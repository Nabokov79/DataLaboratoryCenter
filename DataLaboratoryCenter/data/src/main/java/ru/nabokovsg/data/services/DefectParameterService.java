package ru.nabokovsg.data.services;

import org.springframework.validation.annotation.Validated;
import ru.nabokovsg.data.dto.sizeParameters.NewSizeParametersDto;
import ru.nabokovsg.data.dto.sizeParameters.UpdateSizeParametersDto;
import ru.nabokovsg.data.models.SizeParameters;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface DefectParameterService {

    List<SizeParameters> save(@Valid List<NewSizeParametersDto> parametersDto);

    List<SizeParameters> update(@Valid List<UpdateSizeParametersDto> parametersDto);
}