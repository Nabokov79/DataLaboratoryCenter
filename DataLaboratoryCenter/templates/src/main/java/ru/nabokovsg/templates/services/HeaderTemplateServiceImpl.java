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
import ru.nabokovsg.templates.models.builder.TemplateData;
import ru.nabokovsg.templates.models.enums.DataType;
import ru.nabokovsg.templates.repository.HeaderTemplateRepository;
import ru.nabokovsg.templates.services.factory.StringFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class HeaderTemplateServiceImpl implements HeaderTemplateService {

    private final HeaderTemplateRepository repository;
    private final HeaderTemplateMapper mapper;
    private final StringFactory factory;
    private final TemplateClient client;

    @Override
    public List<HeaderTemplateDto> save(NewHeaderTemplateDto headerDto) {
        Map<Long, HeaderTemplate> headersDb = repository.findAllByReportingDocumentIds(
         headerDto.getReportingDocumentIds()).stream()
                                             .collect(Collectors.toMap(HeaderTemplate::getReportingDocumentId, h -> h));
        HeaderTemplate headerData = set(new HeaderTemplate(), mapper.mapToNewHeaderTemplateDataDto(headerDto));
        List<HeaderTemplate> headers =  repository.saveAll(headerDto.getReportingDocumentIds().stream()
                                                                        .map(id -> {
                                                                            HeaderTemplate header = headersDb.get(id);
                                                                            if (header == null) {
                                                                                header = mapper.mapToHeader(headerData);
                                                                                header.setReportingDocumentId(id);
                                                                            }
                                                                            return header;
                                                                        })
                                                                        .toList());
        if (!headersDb.isEmpty()) {
            headers = Stream.of(headersDb.values(), headers).flatMap(Collection::stream).toList();
        }
        return headers.stream().map(mapper::mapToHeaderTemplateDto).toList();
    }

    @Override
    public HeaderTemplateDto update(UpdateHeaderTemplateDto headerDto) {
        return mapper.mapToHeaderTemplateDto(repository.save(
                set(repository.findById(headerDto.getId()).orElseThrow(() -> new NotFoundException(
                                                String.format("HeaderTemplate with id=%s not found", headerDto.getId())))
                  , mapper.mapToUpdateHeaderTemplateDataDto(headerDto))
        ));
    }

    @Override
    public HeaderTemplateDto get(Long reportingDocumentId) {
        return mapper.mapToHeaderTemplateDto(repository.findByReportingDocumentId(reportingDocumentId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("HeaderTemplate by reportingDocumentId=%s not found", reportingDocumentId)))
        );
    }

    private HeaderTemplate set(HeaderTemplate header, HeaderTemplateDataDto data) {
        OrganizationDto organization = client.getOrganization(data.getOrganizationId());
        setOrganizationData(header, organization, data);
        setBranchData(header, organization, data);
        setDepartmentData(header, organization, data);
        return header;
    }

    private void setOrganizationData(HeaderTemplate header, OrganizationDto organization, HeaderTemplateDataDto data) {
        if (data.getOrganizationFullName()) {
            header.setOrganization(organization.getOrganization());
        } else {
            header.setOrganization(organization.getShortNameOrganization());
        }
        if (data.getOrganizationLicense()) {
            header.setOrganizationLicense(factory.create(new TemplateData.Builder()
                                                                         .type(DataType.LICENSE)
                                                                         .licenses(organization.getLicenses())
                                                                         .build()));
        }
        if (data.getOrganizationContacts()) {
            header.setOrganizationContacts(factory.create(new TemplateData.Builder()
                                                                            .type(DataType.CONTACT)
                                                                            .contact(organization.getContact())
                                                                            .build()));
        }
    }

    private void setBranchData(HeaderTemplate header, OrganizationDto organization, HeaderTemplateDataDto data) {
        BranchDto branch = organization.getBranches()
                .stream()
                .collect(Collectors.toMap(BranchDto::getId, b -> b))
                .get(data.getBranchId());
        if (data.getBranchFullName()) {
            header.setBranch(branch.getBranch());
        } else {
            header.setBranch(branch.getShortNameBranch());
        }
        if (data.getBranchLicense()) {
            header.setOrganizationLicense(factory.create(new TemplateData.Builder()
                                                                         .type(DataType.LICENSE)
                                                                         .licenses(branch.getLicenses())
                                                                         .build()));
        } else {
            header.setOrganizationLicense(null);
        }
        if (data.getBranchContacts()) {
            header.setBranchContacts(factory.create(new TemplateData.Builder()
                                                                      .type(DataType.CONTACT)
                                                                      .contact(branch.getContact())
                                                                      .build()));
        } else {
            header.setBranchContacts(null);
        }
    }

    private void setDepartmentData(HeaderTemplate header, OrganizationDto organization, HeaderTemplateDataDto data) {
        DepartmentDto department = organization.getBranches()
                                                .stream()
                                                .map(BranchDto::getDepartments)
                                                .flatMap(Collection::stream)
                                                .collect(Collectors.toMap(DepartmentDto::getId, d -> d))
                                                .get(data.getDepartmentId());
        if (data.getDepartmentFullName()) {
            header.setDepartment(department.getDepartment());
        } else {
            header.setDepartment(department.getShortNameDepartment());
        }
        if (data.getDepartmentLicense()) {
            header.setDepartmentLicense(factory.create(new TemplateData.Builder()
                                                                       .type(DataType.LICENSE)
                                                                       .licenses(department.getLicenses())
                                                                       .build()));
        } else {
            header.setDepartmentLicense(null);
        }
        if (data.getDepartmentContacts()) {
           header.setDepartmentContacts(factory.create(new TemplateData.Builder()
                                                                         .type(DataType.CONTACT)
                                                                         .address(department.getAddress())
                                                                         .contact(department.getContact())
                                                                         .build()));
        } else {
            header.setDepartmentContacts(null);
        }
    }
}