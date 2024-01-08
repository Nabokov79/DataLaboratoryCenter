package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.objectsSurveyElementData.NewObjectsSurveyElementDataDto;
import ru.nabokovsg.data.dto.objectsSurveyElementData.ObjectsSurveyElementDataDto;
import ru.nabokovsg.data.dto.objectsSurveyElementData.UpdateObjectsSurveyElementDataDto;

import java.util.List;

public interface ObjectsSurveyElementDataService {

    List<ObjectsSurveyElementDataDto> save(Long surveyObjectId, List<NewObjectsSurveyElementDataDto> elementsDataDto);

    List<ObjectsSurveyElementDataDto> update(Long surveyObjectId, List<UpdateObjectsSurveyElementDataDto> elementsDataDto);

    void delete(Long id);
}