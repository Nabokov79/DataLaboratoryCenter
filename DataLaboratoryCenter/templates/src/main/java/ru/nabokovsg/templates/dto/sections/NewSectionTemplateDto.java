package ru.nabokovsg.templates.dto.sections;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового раздела документа")
public class NewSectionTemplateDto {

    @Schema(description = "Индентификаторы типа объекта")
    @NotNull(message = "object type id should not be null")
    @Positive(message = "reporting document id must be positive")
    private Long objectTypeId;
    @Schema(description = "Индентификатор типа отчетного документа")
    @NotNull(message = "reporting document id should not be null")
    @Positive(message = "reporting document id must be positive")
    private Long reportingDocumentId;
    private List<@Valid NewSectionTemplateDataDto> sectionsData;
}