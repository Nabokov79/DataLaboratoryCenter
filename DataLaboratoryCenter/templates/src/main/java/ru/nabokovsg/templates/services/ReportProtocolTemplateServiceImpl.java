package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.TemplateClient;
import ru.nabokovsg.templates.dto.reportProtocol.NewReportProtocolTemplateDto;
import ru.nabokovsg.templates.dto.reportProtocol.ReportProtocolTemplateDto;
import ru.nabokovsg.templates.mappers.ReportProtocolTemplateMapper;
import ru.nabokovsg.templates.models.ReportProtocolTemplate;
import ru.nabokovsg.templates.repository.ReportProtocolTemplateRepository;
import ru.nabokovsg.templates.services.converter.ConverterStringToEnumService;

@Service
@RequiredArgsConstructor
public class ReportProtocolTemplateServiceImpl implements ReportProtocolTemplateService {

    private final ReportProtocolTemplateRepository repository;
    private final ReportProtocolTemplateMapper mapper;
    private final TemplateClient client;
    private final TableTemplateService tableService;
    private final SubsectionTemplateService subsectionService;
    private final ConverterStringToEnumService converter;

    @Override
    public ReportProtocolTemplateDto save(NewReportProtocolTemplateDto protocolDto) {
        ReportProtocolTemplate protocol = repository.findByProtocolType(
                                                        converter.convertToProtocolType(protocolDto.getProtocolType()));
        if (protocol == null) {
            protocol = mapper.mapToNewReportProtocolTemplate(protocolDto
                                                   , client.getReportingDocument(protocolDto.getReportingDocumentId()));
            protocol.setSubsections(subsectionService.getAllById(protocolDto.getSubsectionId()));
            protocol.setTables(tableService.getAllById(protocolDto.getTableIds()));
            protocol = repository.save(protocol);
        }
        return mapper.mapToReportProtocolTemplateDto(protocol);
    }
}