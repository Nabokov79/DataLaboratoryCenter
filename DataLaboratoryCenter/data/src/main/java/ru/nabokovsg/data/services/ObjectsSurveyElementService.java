package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.objectsSurveyElement.NewObjectsSurveyElementDto;
import ru.nabokovsg.data.dto.objectsSurveyElement.ObjectsSurveyElementDto;
import ru.nabokovsg.data.dto.objectsSurveyElement.UpdateObjectsSurveyElementDto;

import java.util.List;

public interface ObjectsSurveyElementService {

    List<ObjectsSurveyElementDto> save(Long surveyObjectId, List<NewObjectsSurveyElementDto> elementsDataDto);

    List<ObjectsSurveyElementDto> update(Long surveyObjectId, List<UpdateObjectsSurveyElementDto> elementsDataDto);

    void delete(Long id);
}