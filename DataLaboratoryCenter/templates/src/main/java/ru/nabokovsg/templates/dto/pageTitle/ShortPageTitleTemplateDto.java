package ru.nabokovsg.templates.dto.pageTitle;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Краткие данные шаблона титульного листа")
public class ShortPageTitleTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Заголовок документа")
    private String documentName;
    @Schema(description = "Название документа")
    private String documentTitle;
}