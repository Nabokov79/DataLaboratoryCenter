package ru.nabokovsg.templates.dto.subsection.subsectionData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для изменения информации в подразделе")
public class UpdateSubsectionTemplateDataDto {

    @Schema(description = "Тип данных")
    @NotBlank(message = "Subsection data type should not be blank")
    private String subsectionDataType;
    @Schema(description = "Указать методическую документацию")
    @NotNull(message = "Methodological document should not be null")
    private Boolean methodologicalDocument;
    @Schema(description = "Указать нормативную документацию")
    @NotNull(message = "Regulatory document should not be null")
    private Boolean regulatoryDocument;
    @Schema(description = "Идентификатор типа объекта")
    private Long objectTypeId;
    @Schema(description = "Индентификатор структурного подразделения организации")
    private Long divisionId;
    @Schema(description = "Пользовательское название структурного подразделения организации")
    private String divisionName;
}