package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDataDto;
import ru.nabokovsg.templates.dto.header.NewHeaderTemplateDto;
import ru.nabokovsg.templates.dto.header.UpdateHeaderTemplateDto;
import ru.nabokovsg.templates.models.HeaderTemplate;

@Mapper(componentModel = "spring")
public interface HeaderTemplateMapper {

    HeaderTemplateDataDto mapToNewHeaderTemplateData(NewHeaderTemplateDto headerDto);

    HeaderTemplateDataDto mapToUpdateHeaderTemplateDataDto(UpdateHeaderTemplateDto headerDto);

    @Mapping(source = "organization", target = "organization")
    @Mapping(source = "license", target = "organizationLicense")
    @Mapping(source = "contacts", target = "organizationContacts")
    @Mapping(target = "id", ignore = true)
    HeaderTemplate mapFromOrganizationData(String organization, String license, String contacts);

    @Mapping(source = "branch", target = "branch")
    @Mapping(source = "license", target = "branchLicense")
    @Mapping(source = "contacts", target = "branchContacts")
    @Mapping(target = "id", ignore = true)
    HeaderTemplate mapFromBranchData(String branch, String license, String contacts);

    @Mapping(source = "department", target = "department")
    @Mapping(source = "license", target = "departmentLicense")
    @Mapping(source = "contacts", target = "departmentContacts")
    @Mapping(target = "id", ignore = true)
    HeaderTemplate mapFromDepartmentData(String department, String license, String contacts);

    @Mapping(source = "headerBranch.branch", target = "branch")
    @Mapping(source = "headerBranch.branchLicense", target = "branchLicense")
    @Mapping(source = "headerBranch.branchContacts", target = "branchContacts")
    @Mapping(source = "headerDepartment.department", target = "department")
    @Mapping(source = "headerDepartment.departmentLicense", target = "departmentLicense")
    @Mapping(source = "headerDepartment.departmentContacts", target = "departmentContacts")
    @Mapping(target = "id", ignore = true)
    HeaderTemplate mapToNewHeaderTemplate(@MappingTarget HeaderTemplate header
                                                       , HeaderTemplate headerBranch
                                                       , HeaderTemplate headerDepartment);

    @Mapping(source = "id", target = "id")
    HeaderTemplate mapToUpdateHeaderTemplate(@MappingTarget HeaderTemplate header, Long id);
}