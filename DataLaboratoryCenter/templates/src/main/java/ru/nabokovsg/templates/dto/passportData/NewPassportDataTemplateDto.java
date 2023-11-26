package ru.nabokovsg.templates.dto.passportData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = " Новый шаблон паспортных данных")
public class NewPassportDataTemplateDto {

    @Schema(description = "Порядковый номер")
    @NotNull(message = "data sequence number must not be null")
    @Positive(message = "data sequence number must be negative")
    private String sequentialNumber;
    @Schema(description = "Наименование данных")
    @NotBlank(message = "data name must not be null")
    private String dataName;
    @Schema(description = "Применить данные в отчете - true - да, false- нет")
    @NotNull(message = "applyProtocol must not be null")
    private Boolean applyReport;
    @Schema(description = "Применить данные в протоколе - true - да, false- нет")
    @NotNull(message = "applyProtocol must not be null")
    private Boolean applyProtocol;
}