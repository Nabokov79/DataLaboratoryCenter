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
import ru.nabokovsg.templates.services.headerDataHandlers.BranchDataService;
import ru.nabokovsg.templates.services.headerDataHandlers.DepartmentDataService;
import ru.nabokovsg.templates.services.headerDataHandlers.OrganizationDataService;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeaderTemplateServiceImpl implements HeaderTemplateService {

    private final HeaderTemplateRepository repository;
    private final HeaderTemplateMapper mapper;
    private final TemplateClient client;
    private final OrganizationDataService organizationDataService;
    private final BranchDataService branchDataService;
    private final DepartmentDataService departmentDataService;

    @Override
    public HeaderTemplateDto save(NewHeaderTemplateDto headerDto) {
        HeaderTemplate header = repository.findByReportingDocumentId(headerDto.getReportingDocumentId());
        if (header == null) {
            header = repository.save(set(new HeaderTemplate(), mapper.mapToNewHeaderTemplateDataDto(headerDto)));
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
        organizationDataService.getHeaderData(header, organization, headerDto);
        branchDataService.getHeaderData(header
                , organization.getBranches()
                        .stream()
                        .collect(Collectors.toMap(BranchDto::getId, b -> b))
                        .get(headerDto.getBranchId())
                , headerDto);
        departmentDataService.getHeaderData(header
                , organization.getBranches()
                        .stream()
                        .map(BranchDto::getDepartments)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toMap(DepartmentDto::getId, d -> d))
                        .get(headerDto.getDepartmentId())
                , headerDto);
        return header;
    }
}