package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.TemplateClient;
import ru.nabokovsg.templates.client.dto.ObjectsTypeDto;
import ru.nabokovsg.templates.dto.subsection.subsectionData.NewSubsectionTemplateDataDto;
import ru.nabokovsg.templates.dto.subsection.subsectionData.SubsectionDataDto;
import ru.nabokovsg.templates.dto.subsection.subsectionData.UpdateSubsectionTemplateDataDto;
import ru.nabokovsg.templates.exceptions.BadRequestException;
import ru.nabokovsg.templates.mappers.SubsectionDataTemplateMapper;
import ru.nabokovsg.templates.models.SubsectionTemplateData;
import ru.nabokovsg.templates.models.enums.DivisionType;
import ru.nabokovsg.templates.models.enums.SubsectionDataType;
import ru.nabokovsg.templates.repository.SubsectionTemplateDataRepository;
import ru.nabokovsg.templates.services.factory.SubsectionDataFactory;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubsectionTemplateDataServiceImpl implements SubsectionTemplateDataService {

    private final SubsectionTemplateDataRepository repository;
    private final SubsectionDataTemplateMapper mapper;
    private final SubsectionDataFactory factory;
    private final TemplateClient client;

    @Override
    public List<SubsectionTemplateData> save(List<NewSubsectionTemplateDataDto> subsectionsData) {
        List<SubsectionTemplateData> subsectionData = new ArrayList<>();
        for (NewSubsectionTemplateDataDto data : subsectionsData) {
            subsectionData.addAll(getData(mapper.mapToSubsectionDataForNew(data)));
        }
        Set<SubsectionTemplateData> subsectionDataDb = repository.findAllBySubsectionData(
                subsectionData.stream()
                        .map(SubsectionTemplateData::getSubsectionData)
                        .toList());
        subsectionData = filter(subsectionData, subsectionDataDb.stream()
                .map(SubsectionTemplateData::getSubsectionData)
                .collect(Collectors.toSet()));
        if (!subsectionData.isEmpty()) {
            if (subsectionDataDb.isEmpty()) {
                return repository.saveAll(subsectionData);
            } else {
                subsectionDataDb.addAll(repository.saveAll(subsectionData));
            }
        }
        return new ArrayList<>(subsectionDataDb);
    }

    @Override
    public List<SubsectionTemplateData> update(UpdateSubsectionTemplateDataDto subsectionData) {
        List<Long> ids = repository.findAllIdSubsectionDataType(subsectionData.getSubsectionDataType());
        List<SubsectionTemplateData> subsectionsData = getData(mapper.mapToSubsectionDataForUpdate(subsectionData));
        List<SubsectionTemplateData> subsectionsDataDb = new ArrayList<>();
        for (int index=0; index < subsectionsData.size(); index++) {
            SubsectionTemplateData data = subsectionsData.get(index);
            data.setId(ids.get(index));
            subsectionsDataDb.add(data);
        }
         return repository.saveAll(subsectionsDataDb);
    }

    private List<SubsectionTemplateData> getData(SubsectionDataDto subsectionData) {
        SubsectionDataType type = convertToSubsectionDataType(subsectionData.getSubsectionDataType());
        switch (type) {
            case NO_DATA -> { return new ArrayList<>(); }
            case ALL_DOCUMENT, REGULATORY_DOCUMENT, METHODOLOGICAL_DOCUMENT ->
            { ObjectsTypeDto objectType = client.getObjectsType(subsectionData.getObjectTypeId());
                return factory.createByDocumentationData(type, objectType.getDocumentations())
                        .stream()
                        .peek(s -> s.setSubsectionDataType(String.valueOf(type)))
                        .toList();
            }
            case LABORATORY_DATA ->
            { return factory.createByDivisionData(convertToDivisionType(subsectionData.getDivisionType())
                            , subsectionData.getDivisionId()
                            , subsectionData.getDivisionName())
                    .stream()
                    .peek(s -> s.setSubsectionDataType(String.valueOf(type)))
                    .toList();
            }
            default -> throw new BadRequestException(String.format("SubsectionData type=%s is not supported", type));
        }
    }

    private List<SubsectionTemplateData> filter(List<SubsectionTemplateData> subsectionsData
            , Set<String> subsectionData) {
        return subsectionsData.stream()
                .filter(s -> !subsectionData.contains(s.getSubsectionData()))
                .toList();
    }

    private SubsectionDataType convertToSubsectionDataType(String subsectionDataType) {
        return SubsectionDataType.from(subsectionDataType)
                .orElseThrow(() -> new BadRequestException(
                        String.format("Unknown subsection data type=%s",subsectionDataType))
                );
    }

    private DivisionType convertToDivisionType(String divisionType) {
        return DivisionType.from(divisionType)
                .orElseThrow(() -> new BadRequestException(
                        String.format("Unknown subsection data type=%s", divisionType))
                );
    }
}