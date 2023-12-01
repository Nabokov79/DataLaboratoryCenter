package ru.nabokovsg.templates.services.builders;

import ru.nabokovsg.templates.client.dto.*;

import java.util.List;

public interface StringBuilderService {

    String convertDocumentation(DocumentationDto documentations);

    String convertLicense(List<LicenseDto> licenses);

    String convertContacts(ContactDto contact, AddressDto address);

    String convertToShortRequisites(ContactDto contact, AddressDto address);

    String convertAddress(AddressDto address);

    String convertEmployee(ShortEmployeeDto employee);

    String convertOrganization(OrganizationDto organization);

    String convertBranch(String divisionName, BranchDto branch);

    String convertDepartment(String divisionName, DepartmentDto department);
}