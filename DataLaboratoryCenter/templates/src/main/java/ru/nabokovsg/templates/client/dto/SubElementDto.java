package ru.nabokovsg.templates.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные подэлемента элемента объекта")
public class SubElementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название подэлемента")
    private String subElementName;
}