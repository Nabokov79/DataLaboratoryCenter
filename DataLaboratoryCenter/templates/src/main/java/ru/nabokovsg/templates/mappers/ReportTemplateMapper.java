package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.nabokovsg.templates.dto.pageTitle.ShortPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.report.ReportTemplateDto;
import ru.nabokovsg.templates.models.PageTitleTemplate;
import ru.nabokovsg.templates.models.ReportTemplate;
import ru.nabokovsg.templates.models.SectionTemplate;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ReportTemplateMapper {

    ReportTemplateDto mapToReportTemplateDto(ReportTemplate reportTemplate);

   @Mappings({
           @Mapping(source = "objectTypeId" , target = "objectTypeId"),
           @Mapping(source = "reportingDocumentId" , target = "reportingDocumentId"),
           @Mapping(source = "pageTitle" , target = "pageTitle"),
           @Mapping(source = "sections" , target = "sections"),
           @Mapping( target = "id", ignore = true)
   })
   ReportTemplate mapToNewReportTemplate(PageTitleTemplate pageTitle
                                        , Set<SectionTemplate> sections
                                        , Long objectTypeId
                                        , Long reportingDocumentId);

    @Mappings({
            @Mapping(source = "id" , target = "id"),
            @Mapping(source = "titleTemplate.documentName" , target = "documentName"),
            @Mapping(source = "titleTemplate.documentTitle" , target = "documentTitle")
    })
    ShortPageTitleTemplateDto mapToShortPageTitleTemplateDto(Long id, PageTitleTemplate titleTemplate);
}