package ru.nabokovsg.templates.dto.subsectionData;

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
@Schema(description = "Данные нового подраздела")
public class NewDivisionDataDto {

    @Schema(description = "Индентификатор подраздела")
    @NotNull(message = "subsection id should not be null")
    @Positive(message = "subsection id can only be positive")
    private Long subsectionId;
    @Schema(description = "Тип данных")
    @NotBlank(message = "subsection data type should not be blank")
    private String subsectionDataType;
    @Schema(description = "Индентификатор структурного подразделения организации")
    @NotNull(message = "division id should not be null")
    @Positive(message = "division id can only be positive")
    private Long divisionId;
    @Schema(description = "Пользовательское название структурного подразделения организации")
    private String divisionName;
    @Schema(description = "Указать адресс структурного подразделения")
    @NotNull(message = "division address should not be null")
    private Boolean address;
    @Schema(description = "Указать лицензию/аттестацию структурного подразделения")
    @NotNull(message = "division license should not be null")
    private Boolean license;
}