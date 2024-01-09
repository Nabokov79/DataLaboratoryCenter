package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.ObjectsIds;
import ru.nabokovsg.data.dto.building.BuildingDto;
import ru.nabokovsg.data.dto.building.NewBuildingDto;
import ru.nabokovsg.data.dto.building.UpdateBuildingDto;
import ru.nabokovsg.data.models.Building;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface BuildingMapper {

    Building mapToNewBuilding(NewBuildingDto buildingDto);

    Building mapToUpdateBuilding(UpdateBuildingDto buildingDto);

    List<BuildingDto> mapToBuildingDto(List<Building> buildings);

    List<BuildingDto> mapToBuildingsDto(Set<Building> buildings);

    ObjectsIds mapFromNewBuildingDto(NewBuildingDto buildingDto);

    ObjectsIds mapFromUpdateBuildingDto(UpdateBuildingDto buildingDto);
}