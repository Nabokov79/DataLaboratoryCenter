package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.TemplateClient;
import ru.nabokovsg.templates.client.dto.DocumentationDto;
import ru.nabokovsg.templates.dto.subsectionData.NewDivisionDataDto;
import ru.nabokovsg.templates.dto.subsectionData.NewDocumentationDataDto;
import ru.nabokovsg.templates.dto.subsectionData.SubsectionTemplateDataDto;
import ru.nabokovsg.templates.dto.subsectionData.NewMeasuringToolDataDto;
import ru.nabokovsg.templates.exceptions.BadRequestException;
import ru.nabokovsg.templates.mappers.SubsectionDataTemplateMapper;
import ru.nabokovsg.templates.models.SubsectionTemplateData;
import ru.nabokovsg.templates.models.enums.SubsectionDataType;
import ru.nabokovsg.templates.repository.SubsectionTemplateDataRepository;
import ru.nabokovsg.templates.services.builders.*;
import ru.nabokovsg.templates.services.converters.ConverterToEnum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class SubsectionTemplateDataServiceImpl implements SubsectionTemplateDataService {

    private final SubsectionTemplateDataRepository repository;
    private final SubsectionDataTemplateMapper mapper;
    private final TemplateClient client;
    private final ConverterToEnum converter;
    private final StringBuilderService stringBuilder;

    @Override
    public SubsectionTemplateDataDto saveDivisionData(NewDivisionDataDto divisionDataDto) {
        return null;
    }

    @Override
    public SubsectionTemplateDataDto saveDocumentationData(NewDocumentationDataDto documentationDataDto) {
        List<DocumentationDto> documentations = client.getObjectsType(documentationDataDto.getObjectTypeId()).getDocumentations();
        if (documentationDataDto.getMethodologicalDocument()) {
            return documentations.stream()
                    .filter(d -> d.getMethodologicalDocument().equals(true))
                    .toList();
        }
        if (documentationDataDto.getRegulatoryDocument()) {
            return documentations.stream()
                    .filter(d -> d.getRegulatoryDocument().equals(true))
                    .toList();
        }
        return null;
    }

    @Override
    public SubsectionTemplateDataDto saveMeasuringToolData(NewMeasuringToolDataDto measuringToolDataDto) {
        return null;
    }


    public List<SubsectionTemplateData> save(String subsectionDataType, NewDivisionDataDto subsectionData) {
        SubsectionDataType type = converter.convertToSubsectionDataType(subsectionDataType);
        Set<SubsectionTemplateData> subsectionsData = repository.findAllBySubsectionDataType(type);
        if (subsectionsData.isEmpty()) {
            subsectionsData = new HashSet<>(repository.saveAll(
                    getDataByType(type, mapper.mapToNewSubsectionDataDto(subsectionData))));
        }
        return subsectionsData.stream().toList();
    }


    public List<SubsectionTemplateDataDto> update(NewMeasuringToolDataDto subsectionData) {
        SubsectionDataType type = converter.convertToSubsectionDataType(subsectionData.getSubsectionDataType());
        List<Long> ids = repository.findAllIdSubsectionDataType(
                converter.convertToSubsectionDataType(subsectionData.getSubsectionDataType())
        );
        List<SubsectionTemplateData> subsectionsData = getDataByType(type
                                                                 , mapper.mapToUpdateSubsectionDataDto(subsectionData));
        List<SubsectionTemplateData> subsectionsDataDb = new ArrayList<>();
        for (int index=0; index < subsectionsData.size(); index++) {
            SubsectionTemplateData data = subsectionsData.get(index);
            data.setId(ids.get(index));
            subsectionsDataDb.add(data);
        }
         return mapper.mapToSubsectionDataDto(repository.saveAll(subsectionsDataDb));
    }

    private List<SubsectionTemplateData> getDataByType(SubsectionDataType type, NewDocumentationDataDto subsectionData) {
        switch (type) {
            case ORGANIZATION -> {
                return List.of(mapper.mapForDivisionData(type
                          , stringBuilder.convertOrganization(client.getOrganization(subsectionData.getDivisionId()))));
            }
            case BRANCH -> {
                return List.of(mapper.mapForDivisionData(type
                                                        , stringBuilder.convertBranch(subsectionData.getDivisionName()
                                                        , client.getBranch(subsectionData.getDivisionId()))));
            }
            case DEPARTMENT -> {
                return List.of(mapper.mapForDivisionData(type
                                                      , stringBuilder.convertDepartment(subsectionData.getDivisionName()
                                                      , client.getDepartment(subsectionData.getDivisionId()))));
            }
            case ALL_DOCUMENT, REGULATORY_DOCUMENT, METHODOLOGICAL_DOCUMENT -> {
                return filterDocumentsData(client.getObjectsType(subsectionData.getObjectTypeId()).getDocumentations()
                                                            , subsectionData.getMethodologicalDocument()
                                                            , subsectionData.getRegulatoryDocument())
                                      .stream()
                                      .map(d -> mapper.mapToSubsectionData(type, subsectionData.getObjectTypeId(), stringBuilder.convertDocumentation(d)))
                        .peek(d -> d.setObjectTypeId(subsectionData.getObjectTypeId()))
                                      .toList();
            }
            default -> throw new BadRequestException(String.format("SubsectionData type=%s is not supported", type));
        }
    }

    private List<DocumentationDto> filterDocumentsData(List<DocumentationDto> documentation
                                                    ,  Boolean methodologicalDocument
                                                    ,  Boolean regulatoryDocument) {
        if (methodologicalDocument) {
            return documentation.stream()
                    .filter(d -> d.getMethodologicalDocument().equals(true))
                    .toList();
        }
        if (regulatoryDocument) {
            return documentation.stream()
                    .filter(d -> d.getRegulatoryDocument().equals(true))
                    .toList();
        }
        return documentation;
    }
}