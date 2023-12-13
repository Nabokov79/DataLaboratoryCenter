package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.TemplateClient;
import ru.nabokovsg.templates.client.dto.DocumentationDto;
import ru.nabokovsg.templates.dto.subsectionDada.*;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.SubsectionDataTemplateMapper;
import ru.nabokovsg.templates.models.SubsectionDataTemplate;
import ru.nabokovsg.templates.models.enums.DivisionType;
import ru.nabokovsg.templates.repository.SubsectionDataTemplateRepository;
import ru.nabokovsg.templates.services.builders.StringBuilderService;
import ru.nabokovsg.templates.services.converter.ConverterStringToEnumService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubsectionDataTemplateServiceImpl implements SubsectionDataTemplateService {

    private final SubsectionDataTemplateRepository repository;
    private final SubsectionDataTemplateMapper mapper;
    private final TemplateClient client;
    private final StringBuilderService stringBuilder;
    private final ConverterStringToEnumService converter;

    @Override
    public SubsectionDataTemplateDto saveDivisionData(NewDivisionDataDto divisionDataDto) {
        DivisionType type = converter.convertToDivisionType(divisionDataDto.getDivisionType());
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
        return mapper. mapToSubsectionDataTemplateDto(repository.save(mapper.mapToSubsectionDataTemplate(division)));
    }

    @Override
    public List<SubsectionDataTemplateDto> saveDocumentationData(NewDocumentationDataDto documentationDataDto) {
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
        List<SubsectionDataTemplate> documentationsData = documentations.stream()
                                                                        .map(stringBuilder::convertDocumentation)
                                                                        .map(mapper::mapToSubsectionDataTemplate)
                                                                        .toList();
        return repository.saveAll(documentationsData).stream()
                                                     .map(mapper::mapToSubsectionDataTemplateDto)
                                                     .toList();
    }

    @Override
    public List<SubsectionDataTemplateDto> saveMeasuringToolData(List<NewMeasuringToolDataDto> measuringToolDataDto) {
        return repository.saveAll(repository.saveAll(measuringToolDataDto.stream()
                                                                         .map(NewMeasuringToolDataDto::getToll)
                                                                         .map(mapper::mapToSubsectionDataTemplate)
                                                                         .toList()))
                                                                 .stream()
                                                                 .map(mapper::mapToSubsectionDataTemplateDto)
                                                                 .toList();
    }

    @Override
    public List<SubsectionDataTemplate> getAllById(List<Long> ids) {
        List<SubsectionDataTemplate> subsectionDataTemplates = repository.findAllById(ids);
        if (subsectionDataTemplates.isEmpty()) {
            throw new NotFoundException(String.format("Subsection data template with ids=%s not found", ids));
        }
        return subsectionDataTemplates;
    }
}