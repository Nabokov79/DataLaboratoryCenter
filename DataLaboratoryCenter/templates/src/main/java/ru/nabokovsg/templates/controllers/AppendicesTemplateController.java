package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.templates.dto.appendices.AppendicesTemplateDto;
import ru.nabokovsg.templates.dto.appendices.NewAppendicesTemplateDto;
import ru.nabokovsg.templates.dto.appendices.UpdateAppendicesTemplateDto;
import ru.nabokovsg.templates.services.AppendicesTemplateService;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/template/appendices",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
@Tag(name="Шаблон приложений документов",
        description="API для работы с данными шаблона приложения документа")
public class AppendicesTemplateController {

    private final AppendicesTemplateService service;

    @Operation(summary = "Данные титульного листа")
    @PostMapping
    public ResponseEntity<AppendicesTemplateDto> save(@RequestBody @Valid NewAppendicesTemplateDto appendicesDto) {
        return ResponseEntity.ok().body(service.save(appendicesDto));
    }

    @Operation(summary = "Изменение данных титульного листа")
    @PatchMapping
    public ResponseEntity<AppendicesTemplateDto> update(@RequestBody @Valid UpdateAppendicesTemplateDto appendicesDto) {
        return ResponseEntity.ok().body(service.update(appendicesDto));
    }
}