package ru.nabokovsg.templates.services.dataHandlers;

import ru.nabokovsg.templates.client.dto.BranchDto;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDataDto;
import ru.nabokovsg.templates.models.HeaderTemplate;

public interface BranchDataService {

    void getHeaderData(HeaderTemplate header, BranchDto branch, HeaderTemplateDataDto data);
}