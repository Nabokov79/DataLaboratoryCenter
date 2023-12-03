package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.templates.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.UpdateSubsectionTemplateDto;
import ru.nabokovsg.templates.services.SubsectionTemplateService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(
        value = "/template/section/subsection",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон подраздела раздела отчета и протокола, заключения",
        description="API для работы с данными шаблона подраздела раздела отчета и протокола, заключения")
public class SubsectionTemplateController {

    private final SubsectionTemplateService service;

    @Operation(summary = "Добавление нового шаблона подраздела раздела отчета")
    @PostMapping
    public ResponseEntity<SubsectionTemplateDto> save(
            @RequestBody @Valid
            @Parameter(description = "Шаблон подраздела") NewSubsectionTemplateDto subsectionsDto) {
        return ResponseEntity.ok().body(service.save(subsectionsDto));
    }

    @Operation(summary = "Добавление нового шаблона подраздела раздела отчета")
    @PatchMapping
    public ResponseEntity<SubsectionTemplateDto> update(
            @RequestBody @Valid
            @Parameter(description = "Шаблон подраздела") UpdateSubsectionTemplateDto subsectionsDto) {
        return ResponseEntity.ok().body(service.update(subsectionsDto));
    }

    @Operation(summary = "Получить подразделы")
    @GetMapping("{sectionId}")
    public ResponseEntity<List<SubsectionTemplateDto>> getAll(
                                        @PathVariable @NotNull
                                        @Positive @Parameter(description = "Индентификатор раздела") Long sectionId) {
        return ResponseEntity.ok().body(service.getAll(sectionId));
    }
}
