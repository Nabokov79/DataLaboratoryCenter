package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.defect.DefectDto;
import ru.nabokovsg.data.dto.defect.NewDefectDto;
import ru.nabokovsg.data.dto.defect.UpdateDefectDto;
import ru.nabokovsg.data.models.Defect;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DefectMapper {

    Defect mapToNewDefect(NewDefectDto defectDto);

    Defect mapToUpdateDefect(UpdateDefectDto defectsDto);

    List<DefectDto> mapToDefectDto(List<Defect> defects);
}
