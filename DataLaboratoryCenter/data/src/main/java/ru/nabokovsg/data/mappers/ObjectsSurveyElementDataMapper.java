package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.objectsSurveyElementData.NewObjectsSurveyElementDataDto;
import ru.nabokovsg.data.dto.objectsSurveyElementData.ObjectsSurveyElementDataDto;
import ru.nabokovsg.data.dto.objectsSurveyElementData.UpdateObjectsSurveyElementDataDto;
import ru.nabokovsg.data.models.SurveyObjectElementData;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ObjectsSurveyElementDataMapper {

    SurveyObjectElementData mapFromNewObjectsSurveyElement(NewObjectsSurveyElementDataDto elementDto);

    SurveyObjectElementData mapFromUpdateObjectsSurveyElement(UpdateObjectsSurveyElementDataDto elementDto);

    List<ObjectsSurveyElementDataDto> mapFromObjectsSurveyElementDto(List<SurveyObjectElementData> elements);
}