package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.client.dto.*;
import ru.nabokovsg.templates.dto.subsectionData.DivisionDataParam;

import java.util.List;

public interface StringBuilderService {

    String convertDocumentation(DocumentationDto documentations);

    String convertLicense(List<LicenseDto> licenses);

    String convertContacts(ContactDto contact, AddressDto address);

    String convertToShortRequisites(ContactDto contact, AddressDto address);

    String convertAddress(AddressDto address);

    String convertEmployee(ShortEmployeeDto employee);

    String convertOrganization(OrganizationDto organization, DivisionDataParam param);

    String convertBranch(BranchDto branch, DivisionDataParam param);

    String convertDepartment(DepartmentDto department, DivisionDataParam param);
}