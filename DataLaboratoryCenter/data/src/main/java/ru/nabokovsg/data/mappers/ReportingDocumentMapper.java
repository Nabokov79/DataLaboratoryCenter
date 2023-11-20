package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.reportingDocument.NewReportingDocumentDto;
import ru.nabokovsg.data.dto.reportingDocument.ReportingDocumentDto;
import ru.nabokovsg.data.dto.reportingDocument.UpdateReportingDocumentDto;
import ru.nabokovsg.data.models.ReportingDocument;

@Mapper(componentModel = "spring")
public interface ReportingDocumentMapper {

    ReportingDocument mapFromNewReportingDocument(NewReportingDocumentDto reportingDocumentDto);

    ReportingDocument mapFromUpdateReportingDocument(UpdateReportingDocumentDto reportingDocumentDto);

    ReportingDocumentDto mapToReportingDocumentDto(ReportingDocument reportingDocument);
}