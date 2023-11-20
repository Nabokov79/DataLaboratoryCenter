package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.contact.NewContactDto;
import ru.nabokovsg.data.dto.contact.NewContactEmployeeDto;
import ru.nabokovsg.data.dto.contact.UpdateContactDto;
import ru.nabokovsg.data.dto.contact.UpdateContactEmployeeDto;
import ru.nabokovsg.data.models.Contact;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    Contact mapToNewRequisites(NewContactDto contactDto);

    Contact mapToUpdateRequisites(UpdateContactDto contactDto);

    NewContactDto mapToNewRequisitesDto(NewContactEmployeeDto contactDto);

    UpdateContactDto mapToUpdateRequisitesDto(UpdateContactEmployeeDto contactDto);
}