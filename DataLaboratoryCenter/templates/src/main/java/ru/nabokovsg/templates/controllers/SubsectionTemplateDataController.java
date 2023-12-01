package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.templates.dto.subsection.subsectionData.NewSubsectionTemplateDataDto;
import ru.nabokovsg.templates.dto.subsection.subsectionData.SubsectionTemplateDataDto;
import ru.nabokovsg.templates.dto.subsection.subsectionData.UpdateSubsectionTemplateDataDto;
import ru.nabokovsg.templates.services.SubsectionTemplateDataService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(
        value = "/template/section/subsection/data",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные подраздела",
        description="API для работы с данными шаблона подраздела")
public class SubsectionTemplateDataController {

    private final SubsectionTemplateDataService service;

    @Operation(summary = "Добавление нового шаблона подраздела раздела отчета")
    @PostMapping
    public ResponseEntity<List<SubsectionTemplateDataDto>> save(
            @RequestBody @Valid
            @Parameter(description = "Данные шаблона подразделов") NewSubsectionTemplateDataDto subsectionsDto) {
        return ResponseEntity.ok().body(service.save(subsectionsDto));
    }

    @Operation(summary = "Добавление нового шаблона подраздела раздела отчета")
    @PatchMapping
    public ResponseEntity<List<SubsectionTemplateDataDto>> update(
            @RequestBody @Valid
            @Parameter(description = "Данные шаблона подразделов") UpdateSubsectionTemplateDataDto subsectionsDto) {
        return ResponseEntity.ok().body(service.update(subsectionsDto));
    }
}