package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.TemplateClient;
import ru.nabokovsg.templates.client.dto.BranchDto;
import ru.nabokovsg.templates.client.dto.DepartmentDto;
import ru.nabokovsg.templates.client.dto.OrganizationDto;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDataDto;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDto;
import ru.nabokovsg.templates.dto.header.NewHeaderTemplateDto;
import ru.nabokovsg.templates.dto.header.UpdateHeaderTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.HeaderTemplateMapper;
import ru.nabokovsg.templates.models.HeaderTemplate;
import ru.nabokovsg.templates.repository.HeaderTemplateRepository;
import ru.nabokovsg.templates.services.builders.StringBuilderService;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeaderTemplateServiceImpl implements HeaderTemplateService {

    private final HeaderTemplateRepository repository;
    private final HeaderTemplateMapper mapper;
    private final TemplateClient client;
    private final StringBuilderService stringBuilder;

    @Override
    public HeaderTemplateDto save(NewHeaderTemplateDto headerDto) {
        HeaderTemplate header = repository.findByReportingDocumentId(headerDto.getReportingDocumentId());
        if (header == null) {
            header = set(new HeaderTemplate(), mapper.mapToNewHeaderTemplateDataDto(headerDto));
            header.setReportingDocumentId(headerDto.getReportingDocumentId());
            header = repository.save(header);
        }
        return mapper.mapToHeaderTemplateDto(header);
    }

    @Override
    public HeaderTemplateDto update(UpdateHeaderTemplateDto headerDto) {
        return mapper.mapToHeaderTemplateDto(repository.save(
                set(repository.findById(headerDto.getId()).orElseThrow(() -> new NotFoundException(
                                String.format("Header template with id=%s not found for update", headerDto.getId())))
                        , mapper.mapToUpdateHeaderTemplateDataDto(headerDto))
        ));
    }

    @Override
    public HeaderTemplateDto get(Long reportingDocumentId) {
        HeaderTemplate header = repository.findByReportingDocumentId(reportingDocumentId);
        if (header == null) {
            throw new NotFoundException(
                    String.format("Header template by reportingDocumentId=%s not found", reportingDocumentId));
        }
        return mapper.mapToHeaderTemplateDto(header);
    }

    private HeaderTemplate set(HeaderTemplate header, HeaderTemplateDataDto headerDto) {
        OrganizationDto organization = client.getOrganization(headerDto.getOrganizationId());
        getHeaderByOrganizationData(header, organization, headerDto);
        getHeaderByBranchData(header
                , organization.getBranches()
                        .stream()
                        .collect(Collectors.toMap(BranchDto::getId, b -> b))
                        .get(headerDto.getBranchId())
                , headerDto);
        getHeaderByDepartmentData(header
                , organization.getBranches()
                        .stream()
                        .map(BranchDto::getDepartments)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toMap(DepartmentDto::getId, d -> d))
                        .get(headerDto.getDepartmentId())
                , headerDto);
        return header;
    }

    private void getHeaderByDepartmentData(HeaderTemplate header
                                         , DepartmentDto department
                                         , HeaderTemplateDataDto data) {
        if (data.getDepartmentFullName()) {
            header.setDepartment(department.getDepartment());
        } else {
            header.setDepartment(department.getShortNameDepartment());
        }
        if (data.getDepartmentLicense()) {
            header.setDepartmentLicense(stringBuilder.convertLicense(department.getLicenses()));
        } else {
            header.setDepartmentLicense(null);
        }
        if (data.getDepartmentContacts()) {
            header.setDepartmentContacts(stringBuilder.convertContacts(department.getContact()
                    , department.getAddress()));
        } else {
            header.setDepartmentContacts(null);
        }
    }

    public void getHeaderByBranchData(HeaderTemplate header
                                    , BranchDto branch
                                    , HeaderTemplateDataDto data) {
        if (data.getBranchFullName()) {
            header.setBranch(branch.getBranch());
        } else {
            header.setBranch(branch.getShortNameBranch());
        }
        if (data.getBranchLicense()) {
            header.setOrganizationLicense(stringBuilder.convertLicense(branch.getLicenses()));
        } else {
            header.setOrganizationLicense(null);
        }
        if (data.getBranchContacts()) {
            header.setBranchContacts(stringBuilder.convertContacts(branch.getContact(), branch.getAddress()));
        } else {
            header.setBranchContacts(null);
        }
    }

    public void getHeaderByOrganizationData(HeaderTemplate header
                                          , OrganizationDto organization
                                          , HeaderTemplateDataDto data) {
        if (data.getOrganizationFullName()) {
            header.setOrganization(organization.getOrganization());
        } else {
            header.setOrganization(organization.getShortNameOrganization());
        }
        if (data.getOrganizationLicense()) {
            header.setOrganizationLicense(stringBuilder.convertLicense(organization.getLicenses()));
        }
        if (data.getOrganizationContacts()) {
            header.setOrganizationContacts(stringBuilder.convertContacts(organization.getContact()
                    , organization.getAddress()));
        }
    }
}