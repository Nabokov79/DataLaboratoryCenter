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
    public SubsectionDataTemplate saveDivisionData(DivisionDataDto divisionDataDto) {
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
        return repository.save(mapper.mapToSubsectionDataTemplate(division));
    }

    @Override
    public List<SubsectionDataTemplate> saveDocumentationData(DocumentationDataDto documentationDataDto) {
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
        return repository.saveAll(documentationsData);
    }

    @Override
    public List<SubsectionDataTemplate> saveMeasuringToolData(List<MeasuringToolDataDto> measuringToolDataDto) {
        return repository.saveAll(measuringToolDataDto.stream()
                                                      .map(MeasuringToolDataDto::getToll)
                                                      .map(mapper::mapToSubsectionDataTemplate)
                                                      .toList());
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