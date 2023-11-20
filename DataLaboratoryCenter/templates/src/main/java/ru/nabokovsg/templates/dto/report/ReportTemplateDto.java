package ru.nabokovsg.templates.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.templates.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.templates.dto.sections.SectionTemplateDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные шаблона отчета")
public class ReportTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Шаблон титульной страницы")
    private PageTitleTemplateDto pageTitle;
    @Schema(description = "Шаблоны разделов")
    private List<SectionTemplateDto> sections;
}