package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.templates.dto.pageTitle.ShortPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.report.ReportTemplateDto;
import ru.nabokovsg.templates.services.ReportTemplateService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/template/report",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон отчета",
        description="API для работы с данными шаблона отчета")
public class ReportTemplateController {

    private final ReportTemplateService service;

    @Operation(summary = "Получить шаблон отчета")
    @GetMapping
    public ResponseEntity<ReportTemplateDto> get(
            @RequestParam @Parameter(description = "Индентификатор типа объекта") Long objectTypeId,
            @RequestParam @Parameter(description = "Индентификатор отчетного документа") Long reportingDocumentId) {
        return ResponseEntity.ok().body(service.get(objectTypeId, reportingDocumentId));
    }

    @Operation(summary = "Получить все шаблоны отчетов")
    @GetMapping("/all")
    public ResponseEntity<List<ShortPageTitleTemplateDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Получить все шаблоны отчетов")
    @PostMapping
    public ResponseEntity<ReportTemplateDto> create(
            @RequestParam @Parameter(description = "Индентификатор типа объекта") Long objectTypeId,
            @RequestParam @Parameter(description = "Индентификатор отчетного документа") Long reportingDocumentId
    ) {
        return ResponseEntity.ok().body(service.create(objectTypeId, reportingDocumentId));
    }
}