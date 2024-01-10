package ru.nabokovsg.data.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.contact.UpdateContactDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о сотруднике")
public class UpdateEmployeeDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id user should not be blank")
    @Positive(message = "id user must be positive")
    private Long id;
    @Schema(description = "Имя")
    @NotBlank(message = "name should not be blank")
    private String name;
    @Schema(description = "Отчество")
    @NotBlank(message = "patronymic should not be blank")
    private String patronymic;
    @Schema(description = "Фамилия")
    @NotBlank(message = "surname should not be blank")
    private String surname;
    @Schema(description = "Должность")
    @NotBlank(message = "post should not be blank")
    private String post;
    @Schema(description = "Контакты сотрудника")
    private UpdateContactDto contact;
    @Schema(description = "Индентификатор организации")
    @NotNull(message = "organization id user should not be blank")
    @Positive(message = "organization id user must be positive")
    private Long organizationId;
    @Schema(description = "Индентификатор филиала организации")
    @NotNull(message = "branch id user should not be blank")
    @Positive(message = "branch id user must be positive")
    private Long branchId;
    @Schema(description = "Индентификатор подразделения филиала организации")
    @NotNull(message = "department id user should not be blank")
    @Positive(message = "department id user must be positive")
    private Long departmentId;
}