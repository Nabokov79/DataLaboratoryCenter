package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.templates.dto.sections.SectionTemplateDto;
import ru.nabokovsg.templates.dto.sections.NewSectionTemplateDto;
import ru.nabokovsg.templates.dto.sections.UpdateSectionTemplateDto;
import ru.nabokovsg.templates.services.SectionTemplateService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(
        value = "/template/section",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон раздела",
        description="API для работы с данными шаблона раздела")
public class SectionTemplateController {

    private final SectionTemplateService service;

    @Operation(summary = "Добавление нового шаблона раздела")
    @PostMapping
    public ResponseEntity<List<SectionTemplateDto>> save(
                                            @RequestBody @Valid
                                            @Parameter(description = "Данные нового шаблона раздела")
                                            NewSectionTemplateDto sectionsDto) {
        return ResponseEntity.ok().body(service.save(sectionsDto));
    }

    @Operation(summary = "Изменение данных шаблона раздела")
    @PatchMapping
    public ResponseEntity<List<SectionTemplateDto>> update(
            @RequestBody @Valid
            @Parameter(description = "Данные шаблона раздела")
            UpdateSectionTemplateDto sectionsDto) {
        return ResponseEntity.ok().body(service.update(sectionsDto));
    }

    @Operation(summary = "Получить заголовок по типу документа")
    @GetMapping("/{reportingDocumentId}")
    public ResponseEntity<List<SectionTemplateDto>> get(@PathVariable @NotNull @Positive Long reportingDocumentId) {
        return ResponseEntity.ok().body(service.get(reportingDocumentId));
    }
}