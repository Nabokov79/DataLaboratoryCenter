package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.nabokovsg.templates.client.dto.ReportingDocumentDto;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.templates.models.PageTitleTemplate;

@Mapper(componentModel = "spring")
public interface PageTitleTemplateMapper {

    @Mappings({
            @Mapping(source = "header", target = "header"),
            @Mapping(source = "reportingDocument.document", target = "title"),
            @Mapping(source = "reportingDocument.documentTitle", target = "heading"),
            @Mapping(source = "signature", target = "signature"),
            @Mapping(source = "year", target = "year"),
            @Mapping(target = "id", ignore = true)
    })
    PageTitleTemplate mapToNewPageTitleTemplate(NewPageTitleTemplateDto pageTitleDto
                                              , HeaderTemplateDto header
                                              , ReportingDocumentDto reportingDocument
                                              , String signature
                                              , String year);

    @Mappings({
            @Mapping(source = "header", target = "header"),
            @Mapping(source = "reportingDocument.document", target = "title"),
            @Mapping(source = "reportingDocument.documentTitle", target = "heading"),
            @Mapping(source = "signature", target = "signature"),
            @Mapping(source = "year", target = "year"),
            @Mapping(source = "id", target = "id")
    })
    PageTitleTemplate mapToUpdatePageTitleTemplate(Long id
                                                 , HeaderTemplateDto header
                                                 , ReportingDocumentDto reportingDocument
                                                 , String signature
                                                 , String year);

    PageTitleTemplateDto mapToPageTitleTemplateDto(PageTitleTemplate pageTitle);
}