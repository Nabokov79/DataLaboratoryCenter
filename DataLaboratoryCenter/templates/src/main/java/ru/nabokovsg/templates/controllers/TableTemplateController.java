package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.templates.dto.table.NewTableTemplateDto;
import ru.nabokovsg.templates.dto.table.TableTemplateDto;
import ru.nabokovsg.templates.dto.table.UpdateTableTemplateDto;
import ru.nabokovsg.templates.services.TableTemplateService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
    @PostMapping("/subsection")
    public ResponseEntity<TableTemplateDto> saveFromSubsectionTemplate(@RequestParam(name = "subsectionId")
                                                                       @NotNull @Positive @Parameter(name = "Индентификатор подраздела") Long subsectionId,
            @RequestBody @Valid
            @Parameter(description = "Данные шаблона таблицы") NewTableTemplateDto tableDto) {
        return ResponseEntity.ok().body(service.saveFromSubsectionTemplate(subsectionId, tableDto));
    }

    @Operation(summary = "Добавление нового шаблона таблицы")
    @PostMapping("/protocol")
    public ResponseEntity<TableTemplateDto> saveFromProtocolTemplate(@RequestParam(name = "protocolId")
                                                                       @NotNull @Positive @Parameter(name = "Индентификатор протокола") Long protocolId,
                                                                       @RequestBody @Valid
                                                                       @Parameter(description = "Данные шаблона таблицы") NewTableTemplateDto tableDto) {
        return ResponseEntity.ok().body(service.saveFromProtocolTemplate(protocolId, tableDto));
    }

    @Operation(summary = "Изменение информации в шаблоне таблицы")
    @PatchMapping
    public ResponseEntity<TableTemplateDto> update(
            @RequestBody @Valid
            @Parameter(description = "Данные шаблона таблицы") UpdateTableTemplateDto tableDto) {
        return ResponseEntity.ok().body(service.update(tableDto));
    }

    @Operation(summary = "Получить таблицу")
    @GetMapping("/{id}")
    public ResponseEntity<TableTemplateDto> get(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Удалить таблицу")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Приложение удалено.");
    }

}