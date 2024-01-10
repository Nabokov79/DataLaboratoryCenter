package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.templates.services.DocumentationDataService;

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

    private final DocumentationDataService service;

//    @Operation(summary = "Добавить данные структурного подразделения")
//    @PostMapping("/division")
//    public ResponseEntity<SubsectionDataTemplateDto> saveDivisionData(
//            @RequestBody @Valid
//            @Parameter(description = "Данные для получения сведений об структурном подраздклении")
//            NewDivisionDataDto divisionDataDto) {
//        return ResponseEntity.ok().body(service.saveDivisionData(divisionDataDto));
//    }
//
//    @Operation(summary = "Добавить данные нормативно-технической документации")
//    @PostMapping("/documentation")
//    public ResponseEntity<List<SubsectionDataTemplateDto>> saveDocumentationData(
//            @RequestBody @Valid
//            @Parameter(description = "Данные для получения документов") NewDocumentationDataDto documentationDataDto) {
//        return ResponseEntity.ok().body(service.saveDocumentationData(documentationDataDto));
//    }
//
//    @Operation(summary = "Добавить данные измерительных инструментов")
//    @PostMapping("/measuringTool")
//    public ResponseEntity<List<SubsectionDataTemplateDto>> saveMeasuringToolData(
//            @RequestBody @Valid
//            @Parameter(description = "Данные для получения сведений об средствах контроля и измерений")
//                                                                   List<NewMeasuringToolDataDto> measuringToolDataDto) {
//        return ResponseEntity.ok().body(service.saveMeasuringToolData(measuringToolDataDto));
//    }
}