package ru.nabokovsg.templates.dto.sections;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные раздела документа")
public class SectionTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер раздела")
    private Integer sequentialSectionNumber;
    @Schema(description = "Название раздела")
    private String sectionName;
//    @Schema(description = "Подразделы")
//    private List<SubsectionTemplateDto> subsections;
//    @Schema(description = "Протоколы раздела документа")
//    private List<ReportProtocolTemplateDto> protocols;
//    @Schema(description = "Рекомендации для объекта обследования")
//    private List<RecommendationTemplateDto> recommendations;
//    @Schema(description = "Приложения документа")
//    private List<AppendicesTemplateDto> appendices;
}