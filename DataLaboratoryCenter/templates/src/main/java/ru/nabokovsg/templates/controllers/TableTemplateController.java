package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.templates.dto.tables.*;
import ru.nabokovsg.templates.services.TableTemplateService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(
        value = "/template/table",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон таблицы",
        description="API для работы с данными шаблона таблицы")
public class TableTemplateController {

    private final TableTemplateService service;

    @Operation(summary = "Добавление нового шаблона таблицы")
    @PostMapping("/protocol")
    public ResponseEntity<TableTemplateDto> saveForProtocol(
                              @RequestBody @Valid
                              @Parameter(description = "Данные шаблона таблицы") NewProtocolTableTemplateDto tableDto) {
        return ResponseEntity.ok().body(service.saveForProtocol(tableDto));
    }

    @Operation(summary = "Добавление нового шаблона таблицы")
    @PostMapping("/subsection")
    public ResponseEntity<TableTemplateDto> saveForSubsection(
                            @RequestBody @Valid
                            @Parameter(description = "Данные шаблона таблицы") NewSubsectionTableTemplateDto tableDto) {
        return ResponseEntity.ok().body(service.saveForSubsection(tableDto));
    }

    @Operation(summary = "Изменение информации в шаблоне таблицы")
    @PatchMapping
    public ResponseEntity<TableTemplateDto> update(
                                   @RequestBody @Valid
                                   @Parameter(description = "Данные шаблона таблицы") UpdateTableTemplateDto tableDto) {
        return ResponseEntity.ok().body(service.update(tableDto));
    }

    @Operation(summary = "Получить все таблицы")
@GetMapping
    public ResponseEntity<List<ShortTableTemplateDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }
}