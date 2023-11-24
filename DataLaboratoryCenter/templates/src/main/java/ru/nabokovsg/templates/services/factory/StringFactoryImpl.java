package ru.nabokovsg.templates.services.factory;

import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.dto.*;
import ru.nabokovsg.templates.exceptions.BadRequestException;
import ru.nabokovsg.templates.models.builder.TemplateData;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StringFactoryImpl implements StringFactory {

    @Override
    public String create(TemplateData data) {
        switch (data.getType()) {
            case LICENSE -> {return  convertLicense(data.getLicenses());}
            case CONTACT -> {return convertContacts(data.getContact(), data.getAddress());}
            case SIGNATURE -> {return convertEmployee(data.getEmployee());}
            case ORGANIZATION -> { return convertOrganization(data.getOrganization());}
            case BRANCH -> {return convertBranch(data.getDivisionName(), data.getBranch());}
            case DEPARTMENT -> {return convertDepartment(data.getDivisionName(), data.getDepartment());}
            case DOCUMENTATION -> {return convertDocumentation(data.getDocument());}
            default -> throw new BadRequestException(String.format("data type=%s is not supported", data.getType()));
        }
    }

    private String convertDocumentation(DocumentationDto documentations) {
        String string = String.join("", "«", documentations.getTitle(), "»");
        if (documentations.getNumber() != null) {
            string = String.join(" ", documentations.getNumber(), string);
        }
        if (documentations.getView() != null) {
            string = String.join(" ", documentations.getView(), string);
        }
        return string;
    }

    private String convertLicense(List<LicenseDto> licenses) {
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

    private String convertContacts(ContactDto contact, AddressDto address) {
        return String.join("//"
        , String.join(", ", String.valueOf(contact.getIndex()), convertAddress(address))
        , String.join(" ","тел./факс", contact.getPhone(),"/", contact.getFax())
        , String.join(" ", "E-mail:", contact.getEmail()));
    }

    private String convertToShortRequisites(ContactDto contact, AddressDto address) {
        return String.join(", ", String.valueOf(contact.getIndex()),  convertAddress(address));
    }

    private String convertAddress(AddressDto address) {
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

    private String convertEmployee(ShortEmployeeDto employee) {
        return String.join("/"
                , employee.getPost()
                , String.join(". ", String.join(".", String.valueOf(employee.getName().charAt(0))
                                                                   , String.valueOf(employee.getPatronymic().charAt(0)))
                                                                    .toUpperCase()
                                         , employee.getSurname()));
    }

    private String convertOrganization(OrganizationDto organization) {
        return String.join(". ", organization.getOrganization()
                , convertToShortRequisites(organization.getContact(), organization.getAddress())
                , convertLicense(organization.getLicenses()));
    }

    private String convertBranch(String divisionName, BranchDto branch) {
        String name = branch.getBranch();
        if (divisionName != null) {
            name = divisionName;
        }
        return String.join(". ", name
                , convertToShortRequisites(branch.getContact(), branch.getAddress())
                , convertLicense(branch.getLicenses()));
    }

    private String convertDepartment(String divisionName, DepartmentDto department) {
        String name = department.getDepartment();
        if (divisionName != null) {
            name = divisionName;
        }
        return String.join(". ", name
                , convertToShortRequisites(department.getContact(), department.getAddress())
                , convertLicense(department.getLicenses()));
    }
}