package ru.nabokovsg.data.dto.subElement;

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
@Schema(description = "Данные для изменения информации о подэлементе элемента объекта")
public class UpdateSubElementDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be blank")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Название подэлемента")
    @NotBlank(message = "sum element name should not be blank")
    private String subElementName;
}