package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.nabokovsg.templates.client.dto.ReportingDocumentDto;
import ru.nabokovsg.templates.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.templates.dto.title.TitleDto;
import ru.nabokovsg.templates.models.HeaderTemplate;
import ru.nabokovsg.templates.models.ProtocolTemplate;
import ru.nabokovsg.templates.models.Title;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProtocolTemplateMapper {

    @Mappings({
            @Mapping(source = "reportingDocument.protocolType", target = "protocolType"),
            @Mapping(source = "reportingDocument.document", target = "documentName"),
            @Mapping(source = "reportingDocument.documentTitle", target = "documentTitle"),
            @Mapping(source = "objectTypeId", target = "objectTypeId"),
            @Mapping(source = "reportingDocument.id", target = "reportingDocumentId"),
            @Mapping(source = "header", target = "header"),
            @Mapping(target = "id", ignore = true)
    })
    ProtocolTemplate mapToNewProtocolTemplate(HeaderTemplate header
                                            , ReportingDocumentDto reportingDocument
                                            , Long objectTypeId);

    ProtocolTemplateDto mapToProtocolTemplateDto(ProtocolTemplate protocolTemplate);

    List<TitleDto> mapToTitleDto(List<Title> titles);
}