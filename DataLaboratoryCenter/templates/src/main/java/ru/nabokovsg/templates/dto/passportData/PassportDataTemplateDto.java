package ru.nabokovsg.templates.dto.passportData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Шаблон паспортных данных")
public class PassportDataTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер")
    private String sequentialNumber;
    @Schema(description = "Наименование данных")
    private String dataName;
    @Schema(description = "Применить данные в отчете")
    private Boolean applyReport;
    @Schema(description = "Применить данные в протоколе")
    private Boolean applyProtocol;
}