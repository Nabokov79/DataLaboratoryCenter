package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.templates.client.dto.ReportingDocumentDto;
import ru.nabokovsg.templates.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.templates.models.HeaderTemplate;
import ru.nabokovsg.templates.models.PageTitleTemplate;

@Mapper(componentModel = "spring")
public interface PageTitleTemplateMapper {

    @Mapping(source = "headerDb", target = "header")
    @Mapping(source = "reportingDocument.name", target = "title")
    @Mapping(source = "reportingDocument.title", target = "heading")
    @Mapping(source = "signature", target = "signature")
    @Mapping(source = "year", target = "year")
    @Mapping(target = "id", ignore = true)
    PageTitleTemplate mapToNewPageTitleTemplate(NewPageTitleTemplateDto pageTitleDto
                                              , HeaderTemplate headerDb
                                              , ReportingDocumentDto reportingDocument
                                              , String signature
                                              , String year);

    @Mapping(source = "headerDb", target = "header")
    @Mapping(source = "reportingDocument.name", target = "title")
    @Mapping(source = "reportingDocument.title", target = "heading")
    @Mapping(source = "signature", target = "signature")
    @Mapping(source = "year", target = "year")
    @Mapping(source = "id", target = "id")
    PageTitleTemplate mapToUpdatePageTitleTemplate(Long id
                                                 , HeaderTemplate headerDb
                                                 , ReportingDocumentDto reportingDocument
                                                 , String signature
                                                 , String year);

    PageTitleTemplateDto mapToPageTitleTemplateDto(PageTitleTemplate pageTitle);
}