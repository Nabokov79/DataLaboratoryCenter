package ru.nabokovsg.templates.dto.protocol;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные шаблона протокола")
public class ProtocolTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название документа")
    private String documentName;
    @Schema(description = "Заголовок документа")
    private String documentTitle;
    @Schema(description = "Шаблон заголовка страницы протокола")
    private HeaderTemplateDto header;
}