package ru.nabokovsg.templates.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные элемента объекта")
public class ElementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название элемента")
    private String elementName;
    @Schema(description = "Подэлементы элемента")
    private List<SubElementDto> subElements;
}