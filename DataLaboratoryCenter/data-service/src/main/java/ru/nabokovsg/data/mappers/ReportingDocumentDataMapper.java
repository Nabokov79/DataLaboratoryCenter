package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.reportingDocumentData.ReportingDocumentDataDto;
import ru.nabokovsg.data.dto.reportingDocumentData.UpdateReportingDocumentDataDto;
import ru.nabokovsg.data.models.Application;
import ru.nabokovsg.data.models.ReportingDocumentData;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportingDocumentDataMapper {

    ReportingDocumentData mapToReportingDocumentData(Application application);

    ReportingDocumentData mapToUpdateReportingDocumentData(UpdateReportingDocumentDataDto dataDto);

    List<ReportingDocumentDataDto> mapToReportingDocumentDataDto(List<ReportingDocumentData> data);
}