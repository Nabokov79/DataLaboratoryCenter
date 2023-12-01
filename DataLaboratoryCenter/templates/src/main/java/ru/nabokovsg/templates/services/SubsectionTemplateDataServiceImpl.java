package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.TemplateClient;
import ru.nabokovsg.templates.client.dto.DocumentationDto;
import ru.nabokovsg.templates.dto.subsection.subsectionData.*;
import ru.nabokovsg.templates.exceptions.BadRequestException;
import ru.nabokovsg.templates.mappers.SubsectionDataTemplateMapper;
import ru.nabokovsg.templates.models.SubsectionTemplateData;
import ru.nabokovsg.templates.models.enums.SubsectionDataType;
import ru.nabokovsg.templates.repository.SubsectionTemplateDataRepository;
import ru.nabokovsg.templates.services.builders.*;
import ru.nabokovsg.templates.services.converters.ConverterToEnum;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SubsectionTemplateDataServiceImpl implements SubsectionTemplateDataService {

    private final SubsectionTemplateDataRepository repository;
    private final SubsectionDataTemplateMapper mapper;
    private final TemplateClient client;
    private final ConverterToEnum converter;
    private final StringBuilderService stringBuilder;
    private final SubsectionTemplateService subsectionService;

    @Override
    public List<SubsectionTemplateDataDto> save(NewSubsectionTemplateDataDto subsectionData) {
        SubsectionDataType type = converter.convertToSubsectionDataType(subsectionData.getSubsectionDataType());
        Set<SubsectionTemplateData> subsectionDataDb =
                          repository.findAllBySubsectionIdAndSubsectionDataType(subsectionData.getSubsectionId(), type);
        if (subsectionDataDb.isEmpty()) {
            List<SubsectionTemplateData> subsectionTemplateData = repository.saveAll(
                    getDataByType(type, mapper.mapToNewSubsectionDataDto(subsectionData)));
            subsectionService.addSubsectionTemplateData(subsectionData.getSubsectionId(), subsectionTemplateData);
            return mapper.mapToSubsectionDataDto(subsectionTemplateData);
        }
        return mapper.mapToSubsectionDataDto(subsectionDataDb.stream().toList());
    }

    @Override
    public List<SubsectionTemplateDataDto> update(UpdateSubsectionTemplateDataDto subsectionData) {
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

    private List<SubsectionTemplateData> getDataByType(SubsectionDataType type, SubsectionDataDto subsectionData) {
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