package ru.nabokovsg.templates.dto.subsectionData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.templates.dto.tables.ShortTableTemplateDto;

@Setter
@Getter
@NoArgsConstructor
public class SubsectionTemplateDataDto {

    @Schema(description = "Тип данных подраздела")
    private String subsectionDataType;
    @Schema(description = "Данные подраздела")
    private String subsectionData;
    @Schema(description = "Таблица подраздела")
    private ShortTableTemplateDto tableTemplate;
}