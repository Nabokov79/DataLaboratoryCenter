package ru.nabokovsg.templates.dto.subsection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные новых подразделов")
public class NewSubsectionTemplateDto {

    @Schema(description = "Индентификатор раздела")
    @NotNull(message = "section id should not be null")
    @Positive(message = "section id can only be positive")
    private Long sectionId;
    @Schema(description = "Порядковый номер подраздела")
    @NotNull(message = "sequential subsection number should not be null")
    @Positive(message = "sequential subsection number can only be positive")
    private double sequentialNumber;
    @Schema(description = "Название подраздела")
    @NotBlank(message = "subsection name should not be blank")
    private String subsectionName;
    @Schema(description = "Текст подраздела")
    private String subsectionText;
    @Schema(description = "Тип данных подраздела")
    @NotBlank(message = "data type subsection should not be blank")
    private String subsectionDataType;
    @Schema(description = "Показать номер подраздела в документе")
    @NotNull(message = "subsection number should not be null")
    private boolean subsectionNumber;
}