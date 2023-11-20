package ru.nabokovsg.data.services;

import org.springframework.validation.annotation.Validated;
import ru.nabokovsg.data.dto.norms.NewNormsDto;
import ru.nabokovsg.data.dto.norms.NormsDto;
import ru.nabokovsg.data.dto.norms.UpdateNormsDto;
import ru.nabokovsg.data.dto.objectsType.ObjectsTypeNormDto;

import java.util.List;

@Validated

public interface NormsService {

    List<ObjectsTypeNormDto> save(List<Long> objectsTypeId, List<NewNormsDto> normsDto);

    List<NormsDto> update(List<UpdateNormsDto> normsDto);
}