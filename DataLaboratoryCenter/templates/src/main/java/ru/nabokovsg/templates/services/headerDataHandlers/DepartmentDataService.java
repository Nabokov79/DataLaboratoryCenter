package ru.nabokovsg.templates.services.headerDataHandlers;

import ru.nabokovsg.templates.client.dto.DepartmentDto;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDataDto;
import ru.nabokovsg.templates.models.HeaderTemplate;

public interface DepartmentDataService {

    void getHeaderData(HeaderTemplate header, DepartmentDto department, HeaderTemplateDataDto data);
}