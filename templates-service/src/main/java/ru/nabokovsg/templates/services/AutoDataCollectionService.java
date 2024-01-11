package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.autoData.AutoDataCollectionDto;
import ru.nabokovsg.templates.models.AutoDataCollection;

import jakarta.validation.Valid;

public interface AutoDataCollectionService {

    AutoDataCollection save(@Valid AutoDataCollectionDto autoDataCollectionDto);

    AutoDataCollection update(AutoDataCollection autoDataCollection
                            , @Valid AutoDataCollectionDto autoDataCollectionDto);
}