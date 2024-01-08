package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.templates.dto.characteristics.CharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.dto.characteristics.NewCharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.dto.characteristics.UpdateCharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.models.CharacteristicsSurveyObject;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacteristicsSurveyObjectMapper {

    List<CharacteristicsSurveyObject> mapToNewCharacteristicsSurveyObject(List<NewCharacteristicsSurveyObjectDto> characteristicsDto);

    List<CharacteristicsSurveyObject> mapToUpdateCharacteristicsSurveyObject(List<UpdateCharacteristicsSurveyObjectDto> characteristicsDto);

    List<CharacteristicsSurveyObjectDto> mapToCharacteristicsSurveyObjectDto(List<CharacteristicsSurveyObject> characteristics);
}