package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.reportProtocol.NewReportProtocolTemplateDto;
import ru.nabokovsg.templates.dto.reportProtocol.ReportProtocolTemplateDto;

public interface ReportProtocolTemplateService {

    ReportProtocolTemplateDto save(NewReportProtocolTemplateDto protocolDto);
}