package ru.nabokovsg.templates.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.templates.dto.subsectionData.NewDivisionDataDto;
import ru.nabokovsg.templates.dto.subsectionData.NewDocumentationDataDto;
import ru.nabokovsg.templates.dto.subsectionData.NewMeasuringToolDataDto;
import ru.nabokovsg.templates.dto.subsectionData.SubsectionTemplateDataDto;
import ru.nabokovsg.templates.services.SubsectionTemplateDataService;
import javax.validation.Valid;

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

    private final SubsectionTemplateDataService service;

    @Operation(summary = "Добавить данные структурного подразделения")
    @PostMapping("/division")
    public ResponseEntity<SubsectionTemplateDataDto> saveDivisionData(
            @RequestBody @Valid
            @Parameter(description = "Данные для получения сведений об структурном подраздклении")
                                                                                  NewDivisionDataDto divisionDataDto) {
        return ResponseEntity.ok().body(service.saveDivisionData(divisionDataDto));
    }

    @Operation(summary = "Добавить данные нормативно-технической документации")
    @PostMapping("/documentation")
    public ResponseEntity<SubsectionTemplateDataDto> saveDocumentationData(
            @RequestBody @Valid
            @Parameter(description = "Данные для получения документов") NewDocumentationDataDto documentationDataDto) {
        return ResponseEntity.ok().body(service.saveDocumentationData(documentationDataDto));
    }

    @Operation(summary = "Добавить данные измерительных инструментов")
    @PostMapping("/measuringTool")
    public ResponseEntity<SubsectionTemplateDataDto> saveMeasuringToolData(
            @RequestBody @Valid
            @Parameter(description = "Данные для получения сведений об средствах контроля и измерений")
                                                                        NewMeasuringToolDataDto measuringToolDataDto) {
        return ResponseEntity.ok().body(service.saveDocumentationData(measuringToolDataDto));
    }
}