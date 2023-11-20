package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.objectsSurveyElement.NewObjectsSurveyElementDto;
import ru.nabokovsg.data.dto.objectsSurveyElement.ObjectsSurveyElementDto;
import ru.nabokovsg.data.dto.objectsSurveyElement.UpdateObjectsSurveyElementDto;
import ru.nabokovsg.data.models.SurveyObjectElement;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ObjectsSurveyElementMapper {

    SurveyObjectElement mapFromNewObjectsSurveyElement(NewObjectsSurveyElementDto elementDto);

    SurveyObjectElement mapFromUpdateObjectsSurveyElement(UpdateObjectsSurveyElementDto elementDto);

    List<ObjectsSurveyElementDto> mapFromObjectsSurveyElementDto(List<SurveyObjectElement> elements);
}