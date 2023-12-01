package ru.nabokovsg.templates.services.dataHandlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.dto.BranchDto;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDataDto;
import ru.nabokovsg.templates.models.HeaderTemplate;
import ru.nabokovsg.templates.services.builders.StringBuilderService;

@Service
@RequiredArgsConstructor
public class BranchDataServiceImpl implements BranchDataService {

    private final StringBuilderService stringBuilder;

    @Override
    public void getHeaderData(HeaderTemplate header, BranchDto branch, HeaderTemplateDataDto data) {
        if (data.getBranchFullName()) {
            header.setBranch(branch.getBranch());
        } else {
            header.setBranch(branch.getShortNameBranch());
        }
        if (data.getBranchLicense()) {
            header.setOrganizationLicense(stringBuilder.convertLicense(branch.getLicenses()));
        } else {
            header.setOrganizationLicense(null);
        }
        if (data.getBranchContacts()) {
            header.setBranchContacts(stringBuilder.convertContacts(branch.getContact(), branch.getAddress()));
        } else {
            header.setBranchContacts(null);
        }
    }
}