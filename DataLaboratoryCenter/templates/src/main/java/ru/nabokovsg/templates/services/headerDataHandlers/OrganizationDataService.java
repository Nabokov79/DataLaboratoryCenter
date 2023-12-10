package ru.nabokovsg.templates.services.headerDataHandlers;

import ru.nabokovsg.templates.client.dto.OrganizationDto;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDataDto;
import ru.nabokovsg.templates.models.HeaderTemplate;

public interface OrganizationDataService {

    void getHeaderData(HeaderTemplate header, OrganizationDto organization, HeaderTemplateDataDto data);
}