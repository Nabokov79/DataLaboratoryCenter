package ru.nabokovsg.templates.dto.sections;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового раздела документа")
public class NewSectionTemplateDto {

    @Schema(description = "Индентификатор типа отчетного документа")
    @NotEmpty(message = "reporting document id should not be null")
    private List<@Positive Long> reportingDocumentIds;
    private List<@Valid NewSectionTemplateDataDto> sectionsData;
}