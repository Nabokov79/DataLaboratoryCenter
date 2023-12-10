package ru.nabokovsg.templates.dto.columns;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о колонки таблицы")
public class UpdateColumnHeaderTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id must be positive")
    private Long id;
    @Schema(description = "Порядковый номер колонки")
    @NotNull(message = "sequential cell number should not be null")
    @Positive(message = "sequential cell number must be positive")
    private Integer sequentialCellNumber;
    @Schema(description = "Название колонки")
    @NotBlank(message = "cell name should not be blank")
    private String cellName;
    @Schema(description = "Тип данных колонки")
    private String columnDataType;
}