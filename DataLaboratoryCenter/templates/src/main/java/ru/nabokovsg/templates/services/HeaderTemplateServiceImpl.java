package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.TemplateClient;
import ru.nabokovsg.templates.client.dto.BranchDto;
import ru.nabokovsg.templates.client.dto.DepartmentDto;
import ru.nabokovsg.templates.client.dto.OrganizationDto;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDataDto;
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
    public HeaderTemplate save(NewHeaderTemplateDto headerDto) {
        HeaderTemplate header = repository.findByReportingDocumentId(headerDto.getReportingDocumentId());
        if (header == null) {
            header = repository.save(set(mapper.mapToNewHeaderTemplateData(headerDto)));
        }
        return header;
    }

    @Override
    public HeaderTemplate update(UpdateHeaderTemplateDto headerDto) {
        if (repository.existsById(headerDto.getId())) {
            return repository.save(
                    mapper.mapToUpdateHeaderTemplate(
                            set(mapper.mapToUpdateHeaderTemplateDataDto(headerDto)), headerDto.getId()
                    )
            );
        }
        throw new NotFoundException(
                String.format("Header template with id=%s not found for update", headerDto.getId())
        );
    }

    private HeaderTemplate set(HeaderTemplateDataDto headerData) {
        OrganizationDto organization = client.getOrganization(headerData.getOrganizationId());
        return mapper.mapToNewHeaderTemplate(getHeaderByOrganizationData(organization, headerData)
                                           , getHeaderByBranchData(
                                                   organization.getBranches()
                                                               .stream()
                                                               .collect(Collectors.toMap(BranchDto::getId, b -> b))
                                                               .get(headerData.getBranchId())
                                                 , headerData)
                                           , getHeaderByDepartmentData(
                                                   organization.getBranches()
                                                               .stream()
                                                               .map(BranchDto::getDepartments)
                                                               .flatMap(Collection::stream)
                                                               .collect(Collectors.toMap(DepartmentDto::getId, d -> d))
                                                               .get(headerData.getDepartmentId())
                                                 , headerData)
                                    );
    }

    public HeaderTemplate getHeaderByOrganizationData(OrganizationDto organizationDto, HeaderTemplateDataDto data) {
        String organization = organizationDto.getShortNameOrganization();
        String license = "";
        String contacts = "";
        if (data.getOrganizationFullName()) {
            organization = organizationDto.getOrganization();
        }
        if (data.getOrganizationLicense()) {
            license = stringBuilder.convertLicense(organizationDto.getLicenses());
        }
        if (data.getOrganizationContacts()) {
            contacts = stringBuilder.convertContacts(organizationDto.getContact()
                    , organizationDto.getAddress());
        }
        return mapper.mapFromOrganizationData(organization, license, contacts);
    }

    public HeaderTemplate getHeaderByBranchData(BranchDto branchDto, HeaderTemplateDataDto data) {
        String branch = branchDto.getShortNameBranch();
        String license = "";
        String contacts = "";
        if (data.getBranchFullName()) {
            branch = branchDto.getBranch();
        }
        if (data.getBranchLicense()) {
            license = stringBuilder.convertLicense(branchDto.getLicenses());
        }
        if (data.getBranchContacts()) {
            contacts = stringBuilder.convertContacts(branchDto.getContact(), branchDto.getAddress());
        }
        return mapper.mapFromBranchData(branch, license, contacts);
    }

    private HeaderTemplate getHeaderByDepartmentData(DepartmentDto departmentDto, HeaderTemplateDataDto data) {
        String department = departmentDto.getShortNameDepartment();
        String license = "";
        String contacts = "";
        if (data.getDepartmentFullName()) {
            department = departmentDto.getDepartment();
        }
        if (data.getDepartmentLicense()) {
            license = stringBuilder.convertLicense(departmentDto.getLicenses());
        }
        if (data.getDepartmentContacts()) {
            contacts = stringBuilder.convertContacts(departmentDto.getContact(), departmentDto.getAddress());
        }

        return mapper.mapFromDepartmentData(department, license, contacts);
    }
}