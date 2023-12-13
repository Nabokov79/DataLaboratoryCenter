package ru.nabokovsg.data.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.branch.UltraShortBranchDto;
import ru.nabokovsg.data.dto.certificate.CertificateDto;
import ru.nabokovsg.data.dto.department.UltraShortDepartmentDto;
import ru.nabokovsg.data.dto.measuringTool.MeasuringToolEmployeeDto;
import ru.nabokovsg.data.dto.organization.UltraShortOrganizationDto;
import ru.nabokovsg.data.dto.contact.ContactEmployeeDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
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
    private ContactEmployeeDto contact;
    @Schema(description = "Организация")
    @NotNull(message = "organization id user should not be blank")
    @Positive(message = "organization id user must be positive")
    private UltraShortOrganizationDto organization;
    @Schema(description = "Филиал организации")
    @NotNull(message = "branch id user should not be blank")
    @Positive(message = "branch id user must be positive")
    private UltraShortBranchDto branch;
    @Schema(description = "Подразделение филиала организации")
    @NotNull(message = "department id user should not be blank")
    @Positive(message = "department id user must be positive")
    private UltraShortDepartmentDto department;
    @Schema(description = "Список сертификатов сотрудника")
    private List<CertificateDto> certificate;
    @Schema(description = "Список средств(приборов) закрепленных за сотрудником")
    private List<MeasuringToolEmployeeDto> measuringTool;
}