package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.templates.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.templates.dto.title.TitleDto;
import ru.nabokovsg.templates.services.ProtocolTemplateService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/template/protocol",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные шаблона протокола/заключения",
        description="API для работы с данными шаблона протокола/заключения")
public class ProtocolTemplateController {

    private final ProtocolTemplateService service;

    @Operation(summary = "Получить шаблон протокола/заключения")
    @GetMapping
    public ResponseEntity<ProtocolTemplateDto> get(
            @RequestParam @Parameter(description = "Индентификатор типа объекта") Long objectsTypeId,
            @RequestParam @Parameter(description = "Индентификатор отчетного документа") Long reportingDocumentId) {
        return ResponseEntity.ok().body(service.get(objectsTypeId, reportingDocumentId));
    }

    @Operation(summary = "Получить шаблон протокола/заключения")
    @GetMapping("/all")
    public ResponseEntity<List<TitleDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }
}