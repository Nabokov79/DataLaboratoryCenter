package ru.nabokovsg.templates.dto.reportProtocol;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового шаблона протокола отчета")
public class NewReportProtocolTemplateDto {

    @Schema(description = "Индентификатор типа объекта")
    @NotNull(message = "object type id should not be null")
    @Positive(message = "object type id can only be positive")
    private Long objectTypeId;
    @Schema(description = "Индентификатор отчетного документа")
    @NotNull(message = "reporting document id should not be null")
    @Positive(message = "reporting document id can only be positive")
    private Long reportingDocumentId;
    @Schema(description = "Тип протокола")
    @NotBlank(message = "protocol type should not be blank")
    private String protocolType;
    @Schema(description = "Порядковый номер протокола")
    @NotNull(message = "sequentialProtocolNumber should not be null")
    @Positive(message = "sequentialProtocolNumber can only be positive")
    private Integer sequentialNumber;
    @Schema(description = "Текст в протоколе")
    private String protocolText;
    @Schema(description = "Индентификаторы подразделов протокола")
    @NotEmpty(message = "subsection ids should not be empty")
    private List<Long> subsectionId;
    @Schema(description = "Индентификаторы таблиц протокола")
    @NotEmpty(message = "table ids should not be empty")
    private List<Long> tableIds;
}