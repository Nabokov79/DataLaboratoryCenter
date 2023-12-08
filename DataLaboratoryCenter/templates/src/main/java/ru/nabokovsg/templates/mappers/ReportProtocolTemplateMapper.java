package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.nabokovsg.templates.client.dto.ReportingDocumentDto;
import ru.nabokovsg.templates.dto.reportProtocol.NewReportProtocolTemplateDto;
import ru.nabokovsg.templates.dto.reportProtocol.ReportProtocolTemplateDto;
import ru.nabokovsg.templates.models.ReportProtocolTemplate;

@Mapper(componentModel = "spring")
public interface ReportProtocolTemplateMapper {

    @Mappings({
            @Mapping(source = "documentDto.protocolType", target = "protocolType"),
            @Mapping(source = "protocolDto.sequentialNumber", target = "sequentialNumber"),
            @Mapping(source = "documentDto.document", target = "protocolName"),
            @Mapping(source = "documentDto.documentTitle", target = "protocolTitle"),
            @Mapping(source = "protocolDto.protocolText", target = "protocolText"),
            @Mapping(target = "id", ignore = true)
    })
    ReportProtocolTemplate mapToNewReportProtocolTemplate(NewReportProtocolTemplateDto protocolDto
                                                                , ReportingDocumentDto documentDto);

    ReportProtocolTemplateDto mapToReportProtocolTemplateDto(ReportProtocolTemplate protocol);
}