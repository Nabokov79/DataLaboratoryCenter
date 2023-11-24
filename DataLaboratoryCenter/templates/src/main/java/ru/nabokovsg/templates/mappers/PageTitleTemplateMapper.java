package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import ru.nabokovsg.templates.client.dto.ReportingDocumentDto;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.UpdatePageTitleTemplateDto;
import ru.nabokovsg.templates.models.PageTitleTemplate;

@Mapper(componentModel = "spring")
public interface PageTitleTemplateMapper {

    PageTitleTemplate mapToNewPageTitleTemplate(NewPageTitleTemplateDto pageTitleDto);

    PageTitleTemplate mapToUpdatePageTitleTemplate(UpdatePageTitleTemplateDto pageTitleDto);

    @Mappings({
            @Mapping(source = "header", target = "pageTitle.header"),
            @Mapping(source = "reportingDocument.document", target = "pageTitle.documentName"),
            @Mapping(source = "reportingDocument.documentTitle", target = "pageTitle.documentTitle"),
            @Mapping(source = "signature", target = "pageTitle.signatureString"),
            @Mapping(source = "year", target = "pageTitle.year"),
            @Mapping(target = "pageTitle.id", ignore = true)
    })
    PageTitleTemplate mapToPageTitleTemplate(@MappingTarget PageTitleTemplate pageTitle
                                                          , HeaderTemplateDto header
                                                          , ReportingDocumentDto reportingDocument
                                                          , String signature
                                                          , String year);

    PageTitleTemplateDto mapToPageTitleTemplateDto(PageTitleTemplate pageTitle);
}