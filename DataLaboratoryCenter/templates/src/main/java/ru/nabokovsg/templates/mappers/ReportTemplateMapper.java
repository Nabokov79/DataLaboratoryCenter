package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.nabokovsg.templates.dto.report.ReportTemplateDto;
import ru.nabokovsg.templates.models.PageTitleTemplate;
import ru.nabokovsg.templates.models.ReportTemplate;

@Mapper(componentModel = "spring")
public interface ReportTemplateMapper {

    ReportTemplateDto mapToReportTemplateDto(ReportTemplate reportTemplate);

   @Mappings({
           @Mapping(source = "objectTypeId" , target = "objectTypeId"),
           @Mapping(source = "reportingDocumentId" , target = "reportingDocumentId"),
           @Mapping(source = "pageTitle" , target = "pageTitle"),
           @Mapping( target = "id", ignore = true)
   })
   ReportTemplate mapToNewReportTemplate(PageTitleTemplate pageTitle
                                        , Long objectTypeId
                                        , Long reportingDocumentId);
}