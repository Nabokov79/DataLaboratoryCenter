package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.templates.dto.reportProtocol.NewReportProtocolTemplateDto;
import ru.nabokovsg.templates.dto.reportProtocol.ReportProtocolTemplateDto;
import ru.nabokovsg.templates.services.ReportProtocolTemplateService;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/template/report/protocol",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон протокола отчета",
        description="API для работы с данными шаблона протокола отчета")
public class ReportProtocolTemplateController {

    private final ReportProtocolTemplateService service;

    @Operation(summary = "Добавить новый шаблон протокола")
    @PostMapping
    public ResponseEntity<ReportProtocolTemplateDto> save(
            @RequestBody @Valid
            @Parameter(description = "Данные нового протокола") NewReportProtocolTemplateDto protocolDto) {
        return ResponseEntity.ok().body(service.save(protocolDto));
    }
}
