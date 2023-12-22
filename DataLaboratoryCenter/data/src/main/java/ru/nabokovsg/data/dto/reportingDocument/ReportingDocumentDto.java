package ru.nabokovsg.data.dto.reportingDocument;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.models.enums.DocumentType;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные типа документа")
public class ReportingDocumentDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название документа")
    private String name;
    @Schema(description = "Заголовок документа")
    private String title;
    @Schema(description = "Тип документа")
    private DocumentType documentType;
}