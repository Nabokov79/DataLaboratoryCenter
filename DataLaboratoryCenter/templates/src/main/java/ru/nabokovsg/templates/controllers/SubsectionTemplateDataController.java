package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.templates.dto.subsectionDada.NewDivisionDataDto;
import ru.nabokovsg.templates.dto.subsectionDada.NewDocumentationDataDto;
import ru.nabokovsg.templates.dto.subsectionDada.NewMeasuringToolDataDto;
import ru.nabokovsg.templates.dto.subsectionDada.SubsectionDataTemplateDto;
import ru.nabokovsg.templates.services.SubsectionDataTemplateService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(
        value = "/template/section/subsection/data",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные подраздела",
        description="API для работы с данными шаблона подраздела")
public class SubsectionTemplateDataController {

    private final SubsectionDataTemplateService service;

    @Operation(summary = "Добавить данные структурного подразделения")
    @PostMapping("/division")
    public ResponseEntity<SubsectionDataTemplateDto> saveDivisionData(
            @RequestBody @Valid
            @Parameter(description = "Данные для получения сведений об структурном подраздклении")
            NewDivisionDataDto divisionDataDto) {
        return ResponseEntity.ok().body(service.saveDivisionData(divisionDataDto));
    }

    @Operation(summary = "Добавить данные нормативно-технической документации")
    @PostMapping("/documentation")
    public ResponseEntity<List<SubsectionDataTemplateDto>> saveDocumentationData(
            @RequestBody @Valid
            @Parameter(description = "Данные для получения документов") NewDocumentationDataDto documentationDataDto) {
        return ResponseEntity.ok().body(service.saveDocumentationData(documentationDataDto));
    }

    @Operation(summary = "Добавить данные измерительных инструментов")
    @PostMapping("/measuringTool")
    public ResponseEntity<List<SubsectionDataTemplateDto>> saveMeasuringToolData(
            @RequestBody @Valid
            @Parameter(description = "Данные для получения сведений об средствах контроля и измерений")
                                                                   List<NewMeasuringToolDataDto> measuringToolDataDto) {
        return ResponseEntity.ok().body(service.saveMeasuringToolData(measuringToolDataDto));
    }
}