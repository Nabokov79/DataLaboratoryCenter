package ru.nabokovsg.templates.dto.sections;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового раздела отчета")
public class NewReportSectionTemplateDto {

    @Schema(description = "Индентификатор титульного листа")
    @NotNull(message = "page title id should not be null")
    @Positive(message = "page title id must be positive")
    private Long pageTitleId;
    @Schema(description = "Раздел отчета")
    @NotNull(message = "section templates should not be null")
    @NotEmpty(message = "section templates should not be empty")
    private List<@Valid NewSectionTemplateDto> sections;
}