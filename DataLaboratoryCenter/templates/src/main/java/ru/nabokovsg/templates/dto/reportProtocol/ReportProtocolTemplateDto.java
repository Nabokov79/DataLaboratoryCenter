package ru.nabokovsg.templates.dto.reportProtocol;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.nabokovsg.templates.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.templates.dto.tables.TableTemplateDto;
import ru.nabokovsg.templates.models.enums.ProtocolType;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные шаблона протокола отчета")
public class ReportProtocolTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Тип протокола")
    private ProtocolType protocolType;
    @Schema(description = "Порядковый номер протокола")
    private Integer sequentialNumber;
    @Schema(description = "Название документа")
    private String protocolName;
    @Schema(description = "Заголовок протокола")
    private String protocolTitle;
    @Schema(description = "Текст в протоколе")
    private String protocolText;
    @Schema(description = "Подразделы протокола")
    private List<SubsectionTemplateDto> subsections;
    @Schema(description = "Таблицы протокола")
    private List<TableTemplateDto> tables;
    //    @Schema(description = "Заключения")
//    private ConclusionTemplate conclusions;
}