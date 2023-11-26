package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.templates.dto.passportData.NewPassportDataTemplateDto;
import ru.nabokovsg.templates.dto.passportData.PassportDataTemplateDto;
import ru.nabokovsg.templates.dto.passportData.UpdatePassportDataTemplateDto;
import ru.nabokovsg.templates.models.PassportDataTemplate;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PassportDataTemplateMapper {

    PassportDataTemplate mapToNewPassportDataTemplate(NewPassportDataTemplateDto passportDataTemplateDto);

    List<PassportDataTemplate> mapToUpdatePassportDataTemplates(List<UpdatePassportDataTemplateDto> passportDataDto);

    PassportDataTemplateDto mapToPassportDataTemplateDto(PassportDataTemplate passportDataTemplate);
}