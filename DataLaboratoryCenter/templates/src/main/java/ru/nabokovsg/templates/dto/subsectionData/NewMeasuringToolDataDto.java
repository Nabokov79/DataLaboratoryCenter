package ru.nabokovsg.templates.dto.subsectionData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для изменения информации в подразделе")
public class NewMeasuringToolDataDto {

    @Schema(description = "Название")
    @NotBlank(message = "toll should not be blank")
    private String toll;
}