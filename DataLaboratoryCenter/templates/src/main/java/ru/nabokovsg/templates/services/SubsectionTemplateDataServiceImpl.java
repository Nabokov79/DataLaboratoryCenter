package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.TemplateClient;
import ru.nabokovsg.templates.client.dto.DocumentationDto;
import ru.nabokovsg.templates.dto.subsectionData.*;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.SubsectionDataTemplateMapper;
import ru.nabokovsg.templates.models.SubsectionTemplateData;
import ru.nabokovsg.templates.models.enums.SubsectionDataType;
import ru.nabokovsg.templates.repository.SubsectionTemplateDataRepository;
import ru.nabokovsg.templates.services.builders.*;
import ru.nabokovsg.templates.services.converter.ConverterStringToEnumService;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubsectionTemplateDataServiceImpl implements SubsectionTemplateDataService {

    private final SubsectionTemplateDataRepository repository;
    private final SubsectionDataTemplateMapper mapper;
    private final TemplateClient client;
    private final StringBuilderService stringBuilder;
    private final SubsectionTemplateService subsectionTemplateService;
    private final ConverterStringToEnumService converter;

    @Override
    public SubsectionTemplateDataDto saveDivisionData(NewDivisionDataDto divisionDataDto) {
        SubsectionDataType type = converter.convertToSubsectionDataType(divisionDataDto.getSubsectionDataType());
        SubsectionTemplateData divisionDataDb = repository.findBySubsectionDataTypeAndDivisionId(
                converter.convertToSubsectionDataType(divisionDataDto.getSubsectionDataType()), divisionDataDto.getDivisionId()
        );
        if (divisionDataDb == null) {
            String division = "";
            DivisionDataParam param = mapper.mapToDivisionDataParam(divisionDataDto);
            switch (type) {
                case ORGANIZATION -> division = stringBuilder.convertOrganization(
                                                        client.getOrganization(divisionDataDto.getDivisionId()), param);
                case BRANCH -> division = stringBuilder.convertBranch(
                                                              client.getBranch(divisionDataDto.getDivisionId()), param);
                case DEPARTMENT -> division = stringBuilder.convertDepartment(
                                                          client.getDepartment(divisionDataDto.getDivisionId()), param);

            }
            if (division.isBlank()) {
                throw new NotFoundException(
                        String.format("Division with parameters type=%s, id=%s not found", type
                                                                                    , divisionDataDto.getDivisionId()));
            }
            SubsectionTemplateData divisionData = repository.save(mapper.mapForDivisionData(type
                                                                                       , divisionDataDto.getDivisionId()
                                                                                       , division));
            subsectionTemplateService.addSubsectionTemplateData(divisionDataDto.getSubsectionId()
                                                              , List.of(divisionData));
            return mapper.mapToSubsectionDataDto(divisionData);
        }
        return mapper.mapToSubsectionDataDto(divisionDataDb);
    }

    @Override
    public List<SubsectionTemplateDataDto> saveDocumentationData(NewDocumentationDataDto documentationDataDto) {
        SubsectionDataType type = converter.convertToSubsectionDataType(documentationDataDto.getSubsectionDataType());
        Set<SubsectionTemplateData> documentationsDataDb = repository.findAllBySubsectionDataTypeAndObjectTypeId(type
                                                                              , documentationDataDto.getObjectTypeId());
        if (documentationsDataDb.isEmpty()) {
            List<DocumentationDto> documentations = client.getObjectsType(documentationDataDto.getObjectTypeId())
                                                                                                   .getDocumentations();
            if (documentationDataDto.getMethodologicalDocument()) {
                documentations = documentations.stream()
                        .filter(d -> d.getMethodologicalDocument().equals(true))
                        .toList();
            }
            if (documentationDataDto.getRegulatoryDocument()) {
                documentations = documentations.stream()
                        .filter(d -> d.getRegulatoryDocument().equals(true))
                        .toList();
            }
            List<SubsectionTemplateData> documentationsData = documentations
                    .stream()
                    .map(d -> mapper.mapToSubsectionData(type
                            , documentationDataDto.getObjectTypeId()
                            , stringBuilder.convertDocumentation(d)))
                    .toList();
            List<SubsectionTemplateData> documentationsDb = repository.saveAll(documentationsData);
            subsectionTemplateService.addSubsectionTemplateData(documentationDataDto.getSubsectionId(), documentationsDb);
            return documentationsDb.stream().map(mapper::mapToSubsectionDataDto).toList();
        }
        return documentationsDataDb.stream().map(mapper::mapToSubsectionDataDto).toList();
    }

    @Override
    public List<SubsectionTemplateDataDto> saveMeasuringToolData(Long subsectionId
                                                                 , List<NewMeasuringToolDataDto> measuringToolDataDto) {
        Set<SubsectionTemplateData> measuringToolData = repository.findAllByToll(
                measuringToolDataDto.stream()
                        .map(NewMeasuringToolDataDto::getSubsectionDataType)
                        .toList()
                , measuringToolDataDto.stream()
                        .map(NewMeasuringToolDataDto::getToll)
                        .toList());
        if (measuringToolData.isEmpty()) {
            List<SubsectionTemplateData> measuringToolDataDb = repository.saveAll(
                    measuringToolDataDto
                            .stream()
                            .map(m -> mapper.mapFromNewMeasuringToolDataDto(
                                                        converter.convertToSubsectionDataType(m.getSubsectionDataType())
                                    , m.getToll())
                            )
                            .toList()
            );
            subsectionTemplateService.addSubsectionTemplateData(subsectionId, measuringToolDataDb);
            return measuringToolDataDb.stream().map(mapper::mapToSubsectionDataDto).toList();

        }
        return measuringToolData.stream()
                .map(mapper::mapToSubsectionDataDto)
                .toList();
    }
}