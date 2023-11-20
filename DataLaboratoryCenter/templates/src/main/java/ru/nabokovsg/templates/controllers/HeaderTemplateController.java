package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDto;
import ru.nabokovsg.templates.dto.header.NewHeaderTemplateDto;
import ru.nabokovsg.templates.dto.header.UpdateHeaderTemplateDto;
import ru.nabokovsg.templates.services.HeaderTemplateService;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/template/header",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон отчета",
        description="API для работы с данными шаблона отчета")
public class HeaderTemplateController {

    private final HeaderTemplateService service;

    @Operation(summary = "Новые данные заголовка")
    @PostMapping
    public ResponseEntity<HeaderTemplateDto> save(@RequestBody @Valid NewHeaderTemplateDto headerDto) {
        return ResponseEntity.ok().body(service.save(headerDto));
    }

    @Operation(summary = "Изменение данных заголовка")
    @PatchMapping
    public ResponseEntity<HeaderTemplateDto> update(@RequestBody @Valid UpdateHeaderTemplateDto headerDto) {
        return ResponseEntity.ok().body(service.update(headerDto));
    }
}