package ru.nabokovsg.data.dto.surveyObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для изменения данных объекта")
public class UpdateSurveyObjectDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be blank")
    @Positive(message = "id must be positive")
    private Long id;
    @Schema(description = "Индентификатор типа объекта")
    @NotNull(message = "id object type must not be null")
    @Positive(message = "id object type must be negative")
    private Long objectTypeId;
    @Schema(description = "Стационарный номер объекта")
    private Integer stationaryNumber;
    @Schema(description = "Индентификатор строения")
    @NotNull(message = "id building must not be null")
    @Positive(message = "id building must be negative")
    private Long buildingId;
}