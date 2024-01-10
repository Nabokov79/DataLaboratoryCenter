package ru.nabokovsg.data.services;

import org.springframework.validation.annotation.Validated;
import ru.nabokovsg.data.dto.contact.NewContactDto;
import ru.nabokovsg.data.dto.contact.UpdateContactDto;
import ru.nabokovsg.data.models.Contact;

import jakarta.validation.Valid;

@Validated
public interface ContactService {

    Contact save(@Valid NewContactDto contactDto);

    Contact update(@Valid UpdateContactDto contactDto);
}