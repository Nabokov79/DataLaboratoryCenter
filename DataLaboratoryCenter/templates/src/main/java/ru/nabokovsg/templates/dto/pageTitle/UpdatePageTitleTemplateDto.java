package ru.nabokovsg.templates.dto.pageTitle;

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
@Schema(description = "Измененные данные титульного листа")
public class UpdatePageTitleTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id must be positive")
    private Long id;
    @Schema(description = "Индентификатор типа объекта")
    @NotNull(message = "object type id should not be null")
    private Long objectTypeId;
    @Schema(description = "Индентификатор типа отчетного документа")
    @NotNull(message = "reporting document id should not be null")
    @Positive(message = "reporting document id must be positive")
    private Long reportingDocumentId;
    @Schema(description = "Строка наименования объекта")
    @NotBlank(message = "object string should not be blank")
    private String object;
    @Schema(description = "Строка наименования места расположения объекта")
    @NotBlank(message = "installation location string should not be blank")
    private String installationLocation;
    @Schema(description = "Строка указания адреса")
    @NotBlank(message = "address string should not be blank")
    private String address;
    @Schema(description = "Индентификатор сотрудника")
    @NotNull(message = "employee id should not be null")
    @Positive(message = "employee id can only be positive")
    private Long employeeId;
    @Schema(description = "Населенный пункт")
    @NotBlank(message = "city should not be blank")
    private String city;
}