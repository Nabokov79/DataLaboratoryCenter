package ru.nabokovsg.templates.services.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.TemplateClient;
import ru.nabokovsg.templates.client.dto.BranchDto;
import ru.nabokovsg.templates.client.dto.DepartmentDto;
import ru.nabokovsg.templates.client.dto.DocumentationDto;
import ru.nabokovsg.templates.client.dto.OrganizationDto;
import ru.nabokovsg.templates.exceptions.BadRequestException;
import ru.nabokovsg.templates.models.SubsectionTemplateData;
import ru.nabokovsg.templates.models.builder.TemplateData;
import ru.nabokovsg.templates.models.enums.DataType;
import ru.nabokovsg.templates.models.enums.DivisionType;
import ru.nabokovsg.templates.models.enums.SubsectionDataType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubsectionDataFactoryImpl implements SubsectionDataFactory {

    private final StringFactory factory;
    private final TemplateClient client;

    @Override
    public List<SubsectionTemplateData> createByDocumentationData(SubsectionDataType type
                                                                , List<DocumentationDto> documentations) {
        switch (type) {
            case ALL_DOCUMENT -> { return convertDocumentationData(documentations);
            }
            case REGULATORY_DOCUMENT -> {
                return convertDocumentationData(documentations.stream()
                                                              .filter(d -> d.getRegulatoryDocument().equals(true))
                                                              .toList());
            }
            case METHODOLOGICAL_DOCUMENT -> {
                return convertDocumentationData(documentations.stream()
                                                              .filter(d -> d.getMethodologicalDocument().equals(true))
                                                              .toList());
            }
            default -> throw new BadRequestException(String.format("data type=%s is not supported", type));
        }
    }

    @Override
    public List<SubsectionTemplateData> createByDivisionData(DivisionType divisionType
                                                           , Long divisionId
                                                           , String divisionName) {
        switch (divisionType) {
            case ORGANIZATION ->  { return convertOrganizationData(client.getOrganization(divisionId));
            }
            case BRANCH -> {  return convertBranchData(divisionName, client.getBranch(divisionId));
            }
            case DEPARTMENT -> { return convertDepartmentData(divisionName, client.getDepartment(divisionId));
            }
            default -> throw new BadRequestException(String.format("data type=%s is not supported", divisionType));
        }
    }

    private List<SubsectionTemplateData> convertDocumentationData(List<DocumentationDto> documentations) {
        return documentations.stream()
                .map(d -> {
                    SubsectionTemplateData template = new SubsectionTemplateData();
                    template.setSubsectionData(factory.create(new TemplateData.Builder()
                                                                              .type(DataType.DOCUMENTATION)
                                                                              .document(d)
                                                                              .build()));
                    return template;
                })
                .toList();
    }

    private List<SubsectionTemplateData> convertOrganizationData(OrganizationDto organization) {
        SubsectionTemplateData data = new SubsectionTemplateData();
        data.setSubsectionData(factory.create(new TemplateData.Builder()
                .type(DataType.ORGANIZATION)
                .organization(organization)
                .build()));
        return List.of(data);
    }

    private List<SubsectionTemplateData> convertBranchData(String divisionName, BranchDto branch) {
        SubsectionTemplateData data = new SubsectionTemplateData();
        data.setSubsectionData(factory.create(new TemplateData.Builder()
                .type(DataType.BRANCH)
                .divisionName(divisionName)
                .branch(branch)
                .build()));
        return List.of(data);
    }

    private List<SubsectionTemplateData> convertDepartmentData(String divisionName, DepartmentDto department) {
        SubsectionTemplateData data = new SubsectionTemplateData();
        data.setSubsectionData(factory.create(new TemplateData.Builder()
                .type(DataType.DEPARTMENT)
                .divisionName(divisionName)
                .department(department)
                .build()));
        return List.of(data);
    }
}