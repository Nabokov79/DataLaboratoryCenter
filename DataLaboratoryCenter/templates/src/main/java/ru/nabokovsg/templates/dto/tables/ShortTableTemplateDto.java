package ru.nabokovsg.templates.dto.tables;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные таблицы раздела или подраздела документа")
public class ShortTableTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Порядковый номер таблицы")
    private Integer sequentialNumber;
    @Schema(description = "Название таблицы")
    private String tableName;
}