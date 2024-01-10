package ru.nabokovsg.data.dto.repairMethod;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные нового способа ремонта")
public class NewRepairMethodDto {

    @Schema(description = "Название способа ремонта")
    @NotBlank(message = "repair method name should not be blank")
    private String methodName;
}