package ru.nabokovsg.templates.dto.tables;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.templates.dto.tables.columns.NewColumnHeaderTemplateDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные новой таблицы подраздела")
public class NewTableTemplateDto {

    @Schema(description = "Тип данных таблицы")
    @NotBlank(message = "table data type should not be blank")
    private String tableDataType;
    @Schema(description = "Порядковый номер таблицы")
    @NotNull(message = "table sequential number should not be null")
    @Positive(message = "table sequential number must be positive")
    private Integer sequentialNumber;
    @Schema(description = "Название таблицы")
    @NotBlank(message = "table name should not be blank")
    private String tableName;
    @Schema(description = "Шаблоны колонок таблицы")
    @NotNull(message = "column headers should not be null")
    @NotEmpty(message = "column headers should not be empty")
    private List<@Valid NewColumnHeaderTemplateDto> columnHeaders;
}
