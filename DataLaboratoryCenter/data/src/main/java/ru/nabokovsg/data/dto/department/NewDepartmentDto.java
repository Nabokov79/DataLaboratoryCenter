package ru.nabokovsg.data.dto.department;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.data.dto.contact.NewContactDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового подразделения филиала организации")
public class NewDepartmentDto {

    @Schema(description = "Полное название")
    @NotBlank(message = "branch should not be blank")
    private String department;
    @Schema(description = "Краткое название")
    private String shortNameDepartment;
    @Schema(description = "Номер подразделения")
    private Integer departmentNumber;
    @Schema(description = "Индентификатор адреса")
    @NotNull(message = "address id should not be blank")
    @Positive(message = "address id can only be positive")
    private Long addressId;
    @Schema(description = "Контыкты")
    private NewContactDto contact;
    @Schema(description = "Индентификатор филиала организации")
    @NotNull(message = "branch id should not be blank")
    @Positive(message = "branch id must be positive")
    private Long branchId;
}