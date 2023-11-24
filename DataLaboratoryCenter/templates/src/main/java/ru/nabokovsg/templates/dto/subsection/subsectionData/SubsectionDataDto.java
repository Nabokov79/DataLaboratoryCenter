package ru.nabokovsg.templates.dto.subsection.subsectionData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SubsectionDataDto {

    @Schema(description = "Тип данных подраздела")
    private String subsectionDataType;
    @Schema(description = "Идентификатор типа объекта")
    private Long objectTypeId;
    @Schema(description = "Индентификатор структурного подразделения организации")
    private Long divisionId;
    @Schema(description = "Вид структурного подразделения организации")
    private String divisionType;
    @Schema(description = "Пользовательское название структурного подразделения организации")
    private String divisionName;
}