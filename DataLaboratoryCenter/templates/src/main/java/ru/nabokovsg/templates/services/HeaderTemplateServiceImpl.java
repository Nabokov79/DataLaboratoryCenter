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
import ru.nabokovsg.templates.services.dataHandlers.BranchDataService;
import ru.nabokovsg.templates.services.dataHandlers.DepartmentDataService;
import ru.nabokovsg.templates.services.dataHandlers.OrganizationDataService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public List<HeaderTemplateDto> save(NewHeaderTemplateDto headerDto) {
        Map<Long, HeaderTemplate> headersDb = repository.findAllByReportingDocumentIds(
         headerDto.getReportingDocumentIds()).stream()
                                             .collect(Collectors.toMap(HeaderTemplate::getReportingDocumentId, h -> h));
        HeaderTemplate headerData = set(new HeaderTemplate(), mapper.mapToNewHeaderTemplateDataDto(headerDto));
        List<HeaderTemplate> headers =  repository.saveAll(headerDto.getReportingDocumentIds()
                                                                        .stream()
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
        return headers.stream()
                      .map(mapper::mapToHeaderTemplateDto)
                      .toList();
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