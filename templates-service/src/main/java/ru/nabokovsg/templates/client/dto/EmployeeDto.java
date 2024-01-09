package ru.nabokovsg.templates.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные о сотруднике")
public class EmployeeDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Имя")
    private String name;
    @Schema(description = "Отчество")
    private String patronymic;
    @Schema(description = "Фамилия")
    private String surname;
    @Schema(description = "Должность")
    private String post;
    @Schema(description = "Контакты сотрудника")
    private ContactDto contact;
    @Schema(description = "Список сертификатов сотрудника")
    private List<CertificateDto> certificate;
    @Schema(description = "Список средств(приборов) закрепленных за сотрудником")
    private List<MeasuringToolEmployeeDto> measuringTool;
}