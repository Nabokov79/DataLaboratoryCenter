package ru.nabokovsg.templates.dto.appendices;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового приложения к документу")
public class NewAppendicesTemplateDto {

    @Schema(description = "Индентификатор типа объекта")
    @NotNull(message = "object type id should not be null")
    @Positive(message = "object type id can only be positive")
    private Long objectTypeId;
    @Schema(description = "Порядковый номер")
    @NotNull(message = "sequential number should not be null")
    @Positive(message = "sequential number can only be positive")
    private Integer sequentialNumber;
    @Schema(description = "Название приложения")
    @NotNull(message = "appendices name should not be blank")
    private String appendicesName;
}