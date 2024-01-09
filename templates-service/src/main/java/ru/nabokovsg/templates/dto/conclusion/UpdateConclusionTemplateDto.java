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
@Schema(description = "Тексты заключений к протоколам для изменения")
public class UpdateConclusionTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id must be positive")
    private Long id;
    @Schema(description = "Основной текст заключения")
    @NotBlank(message = "ifThanNorm should not be blank")
    private String headerText;
    @Schema(description = "Текст заключения при отсутствии дефектов")
    private String ifThanNorm;
    @Schema(description = "Текст заключения при приближении значения к допустимому")
    private String approaching;
    @Schema(description = "Текст заключения при равенстве замера допустимому")
    private String equal;
    @Schema(description = "Текст при отсутствии норм браковки")
    private String ifNoNorm;
    @Schema(description = "Текст закличения при наличии брака")
    private String ifLessNorm;
}
