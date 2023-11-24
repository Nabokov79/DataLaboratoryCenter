package ru.nabokovsg.templates.dto.subsection.subsectionData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового подраздела")
public class NewSubsectionTemplateDataDto {

    @Schema(description = "Тип данных подраздела")
    @NotBlank(message = "data type subsection should not be blank")
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