package ru.nabokovsg.templates.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные подразделения филиала организации")
public class DepartmentDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название")
    private String department;
    @Schema(description = "Краткое название")
    private String shortNameDepartment;
    @Schema(description = "Номер подразделения")
    private Integer departmentNumber;
    @Schema(description = "Адрес")
    private AddressDto address;
    @Schema(description = "Котельная, ЦТП")
    private List<BuildingDto> buildings;
    @Schema(description = "Лицензия, аттестация")
    private List<LicenseDto> licenses;
    @Schema(description = "Контакты")
    private ContactDto contact;
}