package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.ObjectsIds;
import ru.nabokovsg.data.dto.application.ApplicationDto;
import ru.nabokovsg.data.dto.application.NewApplicationDto;
import ru.nabokovsg.data.dto.application.UpdateApplicationDto;
import ru.nabokovsg.data.models.Application;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    Application mapToNewApplication(NewApplicationDto applicationDto);

    ApplicationDto mapToApplicationDto(Application application);

    Application mapToUpdateApplication(UpdateApplicationDto applicationDto);

    List<ApplicationDto> mapToApplicationsDto(List<Application> applications);

    ObjectsIds mapFromNewApplicationDto(NewApplicationDto applicationDto);

    ObjectsIds mapFromUpdateApplicationDto(UpdateApplicationDto applicationDto);
}