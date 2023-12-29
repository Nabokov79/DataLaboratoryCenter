package ru.nabokovsg.templates.dto.subsection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
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
    private String text;
    @Schema(description = "Показать номер подраздела в документе")
    @NotNull(message = "subsection number should not be null")
    private boolean subsectionNumber;
    @Schema(description = "Данные структурного подразделения")
    private DivisionDataDto division;
    @Schema(description = "Данные нормативно-технической документации")
    private DocumentationDataDto documentation;
    @Schema(description = "Данные средст контроля и измерений")
    private List<MeasuringToolDataDto> measuringTools;
}