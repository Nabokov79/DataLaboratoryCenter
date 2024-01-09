package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.organization.NewOrganizationDto;
import ru.nabokovsg.data.dto.organization.OrganizationDto;
import ru.nabokovsg.data.dto.organization.ShortOrganizationDto;
import ru.nabokovsg.data.dto.organization.UpdateOrganizationDto;
import ru.nabokovsg.data.models.Organization;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    Organization mapToNewOrganization(NewOrganizationDto organizationDto);

    Organization mapToUpdateOrganization(UpdateOrganizationDto organizationDto);

    OrganizationDto mapToOrganizationDto(Organization organization);

    Organization mapToOrganization(OrganizationDto organizationDto);

    List<ShortOrganizationDto> mapToShortOrganizationDto(Set<Organization> organization);
}