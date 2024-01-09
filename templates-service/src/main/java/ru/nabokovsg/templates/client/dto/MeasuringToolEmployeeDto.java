package ru.nabokovsg.templates.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Измерительный инструмент(прибор) закрепленный за сотрудником")
public class MeasuringToolEmployeeDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название")
    private String toll;
    @Schema(description = "Модель")
    private String model;
    @Schema(description = "Заводской номер")
    private String workNumber;
}