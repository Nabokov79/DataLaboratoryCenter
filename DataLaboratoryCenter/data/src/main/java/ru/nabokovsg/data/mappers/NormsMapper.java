package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.norms.NewNormsDto;
import ru.nabokovsg.data.dto.norms.NormSearchParametersDto;
import ru.nabokovsg.data.dto.norms.NormsDto;
import ru.nabokovsg.data.dto.norms.UpdateNormsDto;
import ru.nabokovsg.data.models.Norm;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NormsMapper {

    Norm mapToNewNormsDto(NewNormsDto normDto);

    Norm mapToUpdateNormsDto(UpdateNormsDto normDto);

    List<NormsDto> mapToNormsDto(List<Norm> norms);

    NormSearchParametersDto mapToNormSearchParameters(NewNormsDto normDto);
}