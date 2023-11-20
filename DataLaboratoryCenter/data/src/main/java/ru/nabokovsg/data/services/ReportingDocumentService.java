package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.reportingDocument.NewReportingDocumentDto;
import ru.nabokovsg.data.dto.reportingDocument.ReportingDocumentDto;
import ru.nabokovsg.data.dto.reportingDocument.UpdateReportingDocumentDto;

import java.util.List;

public interface ReportingDocumentService {

    List<ReportingDocumentDto> save(List<NewReportingDocumentDto> reportingDocumentDto);

    List<ReportingDocumentDto> update(List<UpdateReportingDocumentDto> reportingDocumentDto);

    ReportingDocumentDto get(Long id);

    List<ReportingDocumentDto> getAll();

    void delete(Long id);
}