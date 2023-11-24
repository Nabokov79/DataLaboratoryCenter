package ru.nabokovsg.templates.dto.subsection.subsectionData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для изменения информации в подразделе")
public class UpdateSubsectionTemplateDataDto {

    @Schema(description = "Индентификатор подраздела")
    @NotNull(message = " subsection id should not be null")
    @Positive(message = "subsection id can only be positive")
    private Long subsectionId;
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