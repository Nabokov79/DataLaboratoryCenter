package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping(
        value = "/template/appendices",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
@Tag(name="Шаблон приложения документа",
        description="API для работы с данными шаблона приложения документа")
public class AppendicesTemplateController {

    private final AppendicesTemplateService service;

    @Operation(summary = "Сохранить новое приложение")
    @PostMapping
    public ResponseEntity<AppendicesTemplateDto> save(
                                              @RequestBody @Valid
                                              @Parameter(name = "Приложение") NewAppendicesTemplateDto appendicesDto) {
        return ResponseEntity.ok().body(service.save(appendicesDto));
    }

    @Operation(summary = "Изменение данных приложения")
    @PatchMapping
    public ResponseEntity<AppendicesTemplateDto> update(
                                            @RequestBody @Valid
                                            @Parameter(name = "Приложение") UpdateAppendicesTemplateDto appendicesDto) {
        return ResponseEntity.ok().body(service.update(appendicesDto));
    }

    @Operation(summary = "Удалить приложение")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Приложение удалено.");
    }
}