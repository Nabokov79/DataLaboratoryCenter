package ru.nabokovsg.templates.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.templates.models.enums.DocumentType;
import ru.nabokovsg.templates.models.enums.ProtocolType;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные типа документа")
public class ReportingDocumentDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название документа")
    private String document;
    @Schema(description = "заголовок документа")
    private String documentTitle;
    @Schema(description = "Тип документа")
    private DocumentType documentType;
    @Schema(description = "Тип протокола")
    private ProtocolType protocolType;
}