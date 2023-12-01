package ru.nabokovsg.templates.services.dataHandlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.dto.DepartmentDto;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDataDto;
import ru.nabokovsg.templates.models.HeaderTemplate;
import ru.nabokovsg.templates.services.builders.StringBuilderService;

@Service
@RequiredArgsConstructor
public class DepartmentDataServiceImpl implements DepartmentDataService {

    private final StringBuilderService stringBuilder;

    @Override
    public void getHeaderData(HeaderTemplate header, DepartmentDto department, HeaderTemplateDataDto data) {
        if (data.getDepartmentFullName()) {
            header.setDepartment(department.getDepartment());
        } else {
            header.setDepartment(department.getShortNameDepartment());
        }
        if (data.getDepartmentLicense()) {
            header.setDepartmentLicense(stringBuilder.convertLicense(department.getLicenses()));
        } else {
            header.setDepartmentLicense(null);
        }
        if (data.getDepartmentContacts()) {
            header.setDepartmentContacts(stringBuilder.convertContacts(department.getContact()
                                                                     , department.getAddress()));
        } else {
            header.setDepartmentContacts(null);
        }
    }
}