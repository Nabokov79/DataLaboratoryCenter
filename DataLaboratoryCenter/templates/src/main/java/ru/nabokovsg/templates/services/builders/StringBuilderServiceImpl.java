package ru.nabokovsg.templates.services.builders;

import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.dto.*;
import ru.nabokovsg.templates.dto.subsectionDada.DivisionDataParam;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StringBuilderServiceImpl implements StringBuilderService {

    @Override
    public String convertDocumentation(DocumentationDto documentations) {
        String string = String.join("", "«", documentations.getTitle(), "»");
        if (documentations.getNumber() != null) {
            string = String.join(" ", documentations.getNumber(), string);
        }
        if (documentations.getView() != null) {
            string = String.join(" ", documentations.getView(), string);
        }
        return string;
    }

    @Override
    public String convertLicense(List<LicenseDto> licenses) {
        LicenseDto license = licenses.stream()
                .filter(l -> l.getEndData().isAfter(LocalDate.now()))
                .toList()
                .get(0);
        if (license == null) {
            license = licenses.stream()
                    .max(Comparator.comparing(LicenseDto::getEndData))
                    .orElseThrow(NoSuchElementException::new);
        }
        return String.join(" ", license.getDocumentType(),
                                                  "№",
                                                  license.getLicenseNumber(),
                                                  "от",
                                                  license.getStartData().toString());
    }

    @Override
    public String convertContacts(ContactDto contact, AddressDto address) {
        return String.join("//"
        , String.join(", ", String.valueOf(contact.getIndex()), convertAddress(address))
        , String.join(" ","тел./факс", contact.getPhone(),"/", contact.getFax())
        , String.join(" ", "E-mail:", contact.getEmail()));
    }

    @Override
    public String convertToShortRequisites(ContactDto contact, AddressDto address) {
        return String.join(", ", String.valueOf(contact.getIndex()),  convertAddress(address));
    }

    @Override
    public String convertAddress(AddressDto address) {
        String string = String.join(", ", address.getCity()
                                                , String.join(" ", address.getStreet()
                                                                     , "д.", String.valueOf(address.getHouseNumber())));
        if (address.getBuildingNumber() != null) {
            string = String.join(", ", string, String.join(""
                                                     , "корп.", String.valueOf(address.getBuildingNumber())));
        }
        if (address.getLetter() != null) {
            string = String.join(", ", string, String.join("", "лит.", address.getLetter()));
        }
        return string;
    }

    @Override
    public String convertEmployee(ShortEmployeeDto employee) {
        return String.join("/"
                , employee.getPost()
                , String.join(". ", String.join(".", String.valueOf(employee.getName().charAt(0))
                                                                   , String.valueOf(employee.getPatronymic().charAt(0)))
                                                                    .toUpperCase()
                                         , employee.getSurname()));
    }

    @Override
    public String convertOrganization(OrganizationDto organization, DivisionDataParam param) {
        return String.join(". ", organization.getOrganization()
                , convertToShortRequisites(organization.getContact(), organization.getAddress())
                , convertLicense(organization.getLicenses()));
    }

    @Override
    public String convertBranch(BranchDto branch, DivisionDataParam param) {
        String divisionData = getDivisionName(branch.getBranch(), param.getDivisionName());
        if (param.getAddress()) {
            divisionData = String.join(". ", divisionData
                                                    , convertContacts(branch.getContact(), branch.getAddress()));

        }
        if (param.getLicense()) {
            divisionData = String.join(". ", divisionData, convertLicense(branch.getLicenses()));
        }
        return divisionData;
    }

    @Override
    public String convertDepartment(DepartmentDto department, DivisionDataParam param) {
        String divisionData = getDivisionName(department.getDepartment(), param.getDivisionName());
        if (param.getAddress()) {
            divisionData = String.join(". ", divisionData
                                                   , convertContacts(department.getContact(), department.getAddress()));

        }
        if (param.getLicense()) {
            divisionData = String.join(". ", divisionData, convertLicense(department.getLicenses()));
        }
        return divisionData;
    }

    private String getDivisionName(String name, String divisionName){
        if (divisionName != null) {
            return divisionName;
        }
        return name;
    }
}