package ru.nabokovsg.data.dto.department;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Основные сведения о подразделении филиала организации")
public class UltraShortDepartmentDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название")
    private String department;
    @Schema(description = "Краткое название")
    private String shortNameDepartment;
    @Schema(description = "Номер подразделения")
    private Integer departmentNumber;
}