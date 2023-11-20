package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.ObjectsIds;
import ru.nabokovsg.data.dto.surveyObject.NewSurveyObjectDto;
import ru.nabokovsg.data.dto.surveyObject.ShortSurveyObjectDto;
import ru.nabokovsg.data.dto.surveyObject.SurveyObjectDto;
import ru.nabokovsg.data.dto.surveyObject.UpdateSurveyObjectDto;
import ru.nabokovsg.data.models.SurveyObject;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SurveyObjectMapper {

    SurveyObject mapToNewObjectSurvey(NewSurveyObjectDto objectDto);

    SurveyObject mapToUpdateObjectSurvey(UpdateSurveyObjectDto objectDto);

    List<ShortSurveyObjectDto> mapToObjectSurveyDtos(List<SurveyObject> objects);

    SurveyObjectDto mapToObjectSurveyDto(SurveyObject object);

    List<ObjectsIds> mapFromNewObjectSurveyIds(List<NewSurveyObjectDto> objectsDto);

    List<ObjectsIds> mapFromUpdateObjectSurveyIds(List<UpdateSurveyObjectDto> objectsDto);
}