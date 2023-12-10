package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;;
import ru.nabokovsg.templates.dto.recommendation.NewRecommendationTemplateDto;
import ru.nabokovsg.templates.dto.recommendation.RecommendationTemplateDto;
import ru.nabokovsg.templates.dto.recommendation.UpdateRecommendationTemplateDto;
import ru.nabokovsg.templates.services.RecommendationTemplateService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(
        value = "/template/recommendation",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон рекомендаций по утранению дефектов, замечаний",
        description="API для работы с данными шаблона рекомендаций по утранению дефектов, замечаний")
public class RecommendationTemplateController {

    private final RecommendationTemplateService service;

    @Operation(summary = "Новые данные заголовка")
    @PostMapping
    public ResponseEntity<RecommendationTemplateDto> save(@RequestBody @Valid NewRecommendationTemplateDto recommendationDto) {
        return ResponseEntity.ok().body(service.save(recommendationDto));
    }

    @Operation(summary = "Изменение данных заголовка")
    @PatchMapping
    public ResponseEntity<RecommendationTemplateDto> update(@RequestBody @Valid UpdateRecommendationTemplateDto recommendationDto) {
        return ResponseEntity.ok().body(service.update(recommendationDto));
    }

    @Operation(summary = "Получить заголовок по типу документа")
    @GetMapping("/{objectTypeId}")
    public ResponseEntity<List<RecommendationTemplateDto>> getAll(@PathVariable @NotNull @Positive Long objectTypeId) {
        return ResponseEntity.ok().body(service.getAll(objectTypeId));
    }
}