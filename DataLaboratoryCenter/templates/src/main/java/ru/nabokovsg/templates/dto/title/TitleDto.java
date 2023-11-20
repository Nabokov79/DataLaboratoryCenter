package ru.nabokovsg.templates.dto.title;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TitleDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Заголовок документа")
    private String documentName;
    @Schema(description = "Название документа")
    private String documentTitle;
}
