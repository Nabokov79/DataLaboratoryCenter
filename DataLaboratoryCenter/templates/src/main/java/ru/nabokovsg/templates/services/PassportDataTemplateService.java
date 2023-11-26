package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.passportData.NewPassportDataTemplateDto;
import ru.nabokovsg.templates.dto.passportData.PassportDataTemplateDto;
import ru.nabokovsg.templates.dto.passportData.UpdatePassportDataTemplateDto;
import java.util.List;

public interface PassportDataTemplateService {

    List<PassportDataTemplateDto> save(List<NewPassportDataTemplateDto> passportDataDto);

    List<PassportDataTemplateDto> update(List<UpdatePassportDataTemplateDto> passportDataDto);

    List<PassportDataTemplateDto> getAll();
}