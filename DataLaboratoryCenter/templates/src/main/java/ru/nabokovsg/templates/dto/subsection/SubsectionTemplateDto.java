package ru.nabokovsg.templates.dto.subsection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.templates.dto.subsectionData.SubsectionTemplateDataDto;
import ru.nabokovsg.templates.dto.tables.TableTemplateDto;
import ru.nabokovsg.templates.models.enums.SubsectionDataType;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные подраздела раздела отчета, рподаздела протокола, подраздела заключения")
public class SubsectionTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип данных подраздела")
    private SubsectionDataType subsectionDataType;
    @Schema(description = "Порядковый номер подраздела")
    private double sequentialNumber;
    @Schema(description = "Название подраздела")
    private String subsectionName;
    @Schema(description = "Текст подраздела")
    private String subsectionText;
    @Schema(description = "Данные подраздела")
    private List<SubsectionTemplateDataDto> subsectionData;
    @Schema(description = "Шаблон таблицы")
    private TableTemplateDto table;
//    @Schema(description = "Шаблон рекомендаций")
//    private List<RecommendationTemplateDto> recommendations;
//    @Schema(description = "Шаблон заключений")
//    private ConclusionTemplateDto conclusionsTemplate;
    @Schema(description = "Показать номер подраздела в документе")
    private boolean subsectionNumber;
}