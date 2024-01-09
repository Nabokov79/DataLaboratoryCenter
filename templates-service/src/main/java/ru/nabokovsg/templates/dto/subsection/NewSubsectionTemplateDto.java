package ru.nabokovsg.templates.dto.subsection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.nabokovsg.templates.dto.autoData.AutoDataCollectionDto;
import ru.nabokovsg.templates.dto.subsectionDada.DivisionDataDto;
import ru.nabokovsg.templates.dto.subsectionDada.DocumentationDataDto;
import ru.nabokovsg.templates.dto.subsectionDada.MeasuringToolDataDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового подраздела")
public class NewSubsectionTemplateDto {

    @Schema(description = "Название подраздела")
    @NotBlank(message = "subsection name should not be blank")
    private String subsectionName;
    @Schema(description = "Порядковый номер подраздела")
    @NotNull(message = "sequential subsection number should not be null")
    @Positive(message = "sequential subsection number can only be positive")
    private double sequentialNumber;
    @Schema(description = "Текст пользователя")
    private String userText;
    @Schema(description = "Показать номер подраздела в документе")
    @NotNull(message = "sequential number visible should not be null")
    private boolean sequentialNumberVisible;
    @Schema(description = "индентификатор сотрудника")
    private Long employeeId;
    @Schema(description = "Данные структурного подразделения")
    private DivisionDataDto division;
    @Schema(description = "Данные нормативно-технической документации")
    private DocumentationDataDto documentation;
    @Schema(description = "Данные средств контроля и измерений")
    private List<MeasuringToolDataDto> measuringTools;
    @Schema(description = "Данные для атоматического сбора и записи информации")
    private AutoDataCollectionDto autoDataCollection;

    @Override
    public String toString() {
        return "NewSubsectionTemplateDto{" +
                "subsectionName='" + subsectionName + '\'' +
                ", sequentialNumber=" + sequentialNumber +
                ", userText='" + userText + '\'' +
                ", sequentialNumberVisible=" + sequentialNumberVisible +
                ", employeeId=" + employeeId +
                ", division=" + division +
                ", documentation=" + documentation +
                ", measuringTools=" + measuringTools +
                ", autoDataCollection=" + autoDataCollection +
                '}';
    }
}