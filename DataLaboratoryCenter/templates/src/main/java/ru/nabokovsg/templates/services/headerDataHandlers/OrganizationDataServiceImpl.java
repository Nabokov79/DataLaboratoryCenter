package ru.nabokovsg.templates.services.headerDataHandlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.dto.OrganizationDto;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDataDto;
import ru.nabokovsg.templates.models.HeaderTemplate;
import ru.nabokovsg.templates.services.builders.StringBuilderService;

@Service
@RequiredArgsConstructor
public class OrganizationDataServiceImpl implements OrganizationDataService {

    private final StringBuilderService stringBuilder;

    @Override
    public void getHeaderData(HeaderTemplate header, OrganizationDto organization, HeaderTemplateDataDto data) {
        if (data.getOrganizationFullName()) {
            header.setOrganization(organization.getOrganization());
        } else {
            header.setOrganization(organization.getShortNameOrganization());
        }
        if (data.getOrganizationLicense()) {
            header.setOrganizationLicense(stringBuilder.convertLicense(organization.getLicenses()));
        }
        if (data.getOrganizationContacts()) {
            header.setOrganizationContacts(stringBuilder.convertContacts(organization.getContact()
                                                                       , organization.getAddress()));
        }
    }
}