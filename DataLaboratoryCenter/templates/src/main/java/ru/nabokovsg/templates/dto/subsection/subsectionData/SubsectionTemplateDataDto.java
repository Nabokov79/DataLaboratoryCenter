package ru.nabokovsg.templates.dto.subsection.subsectionData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные подраздела")
public class SubsectionTemplateDataDto {

    @Schema(description = "Индентификатор")
    private long id;
    private String subsectionDataType;
    @Schema(description = "Данные подраздела")
    private String subsectionData;
}