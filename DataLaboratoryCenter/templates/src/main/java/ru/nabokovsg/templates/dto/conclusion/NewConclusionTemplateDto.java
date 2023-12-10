package ru.nabokovsg.templates.dto.conclusion;

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
@Schema(description = "Тексты новых заключений к протоколам")
public class NewConclusionTemplateDto {

    @Schema(description = "Индентификатор типа объекта")
    @NotNull(message = "object type id should not be null")
    private Long objectTypeId;
    @Schema(description = "Индентификатор типа отчетного документа")
    @NotNull(message = "reporting document id should not be null")
    @Positive(message = "reporting document id must be positive")
    private Long reportingDocumentId;
    @Schema(description = "Текст заключения при отсутствии дефектов")
    @NotBlank(message = "ifThanNorm should not be blank")
    private String ifThanNorm;
    @Schema(description = "Текст заключения при приближении значения к допустимому")
    @NotBlank(message = "approaching should not be blank")
    private String approaching;
    @Schema(description = "Текст заключения при равенстве замера допустимому")
    @NotBlank(message = "equal should not be blank")
    private String equal;
    @Schema(description = "Текст при отсутствии норм браковки")
    @NotBlank(message = "ifNoNorm should not be blank")
    private String ifNoNorm;
    @Schema(description = "Текст закличения при наличии брака")
    @NotBlank(message = "ifLessNorm should not be blank")
    private String ifLessNorm;
}