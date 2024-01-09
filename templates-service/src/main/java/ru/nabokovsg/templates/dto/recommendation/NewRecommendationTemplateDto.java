package ru.nabokovsg.templates.dto.recommendation;

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
@Schema(description = "Данные новой рекомендации")
public class NewRecommendationTemplateDto {

    @Schema(description = "Индентификатор отчета иои протокола")
    @NotNull(message = "id(section/protocol) should not be null")
    @Positive(message = "id(section/protocol) can only be positive")
    private Long id;
    @Schema(description = "Индентификатор типа объекта")
    @NotNull(message = "object type id should not be null")
    @Positive(message = "object type id can only be positive")
    Long objectTypeId;
    @Schema(description = "Текст рекомендации")
    @NotBlank(message = "recommendation text should not be blank")
    String recommendationText;
}