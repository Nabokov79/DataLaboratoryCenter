package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.templates.dto.conclusion.ConclusionTemplateDto;
import ru.nabokovsg.templates.dto.conclusion.NewConclusionTemplateDto;
import ru.nabokovsg.templates.dto.conclusion.UpdateConclusionTemplateDto;
import ru.nabokovsg.templates.services.ConclusionTemplateService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping(
        value = "/template/conclusion",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
@Tag(name="Шаблон заключений",
        description="API для работы с данными шаблона заключений к протоколам")
public class ConclusionTemplateController {

    private final ConclusionTemplateService service;

    @Operation(summary = "Данные шыблона новых заключений")
    @PostMapping
    public ResponseEntity<ConclusionTemplateDto> save(@RequestBody @Valid NewConclusionTemplateDto conclusionDto) {
        return ResponseEntity.ok().body(service.save(conclusionDto));
    }

    @Operation(summary = "Изменение данных шаблона заключений")
    @PatchMapping
    public ResponseEntity<ConclusionTemplateDto> update(@RequestBody @Valid UpdateConclusionTemplateDto conclusionDto) {
        return ResponseEntity.ok().body(service.update(conclusionDto));
    }

    @Operation(summary = "Изменение данных шаблона заключений")
    @GetMapping
    public ResponseEntity<ConclusionTemplateDto> getBy(
                                     @RequestParam("objectTypeId") @NotNull @Positive Long objectTypeId,
                                     @RequestParam("reportingDocumentId") @NotNull @Positive Long reportingDocumentId) {
        return ResponseEntity.ok().body(service.getBy(objectTypeId, reportingDocumentId));
    }
}