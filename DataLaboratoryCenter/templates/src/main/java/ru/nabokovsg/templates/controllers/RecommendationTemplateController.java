package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
@Tag(name="Шаблон рекомендаций по устранению дефектов, замечаний",
        description="API для работы с данными шаблона рекомендаций по устранению дефектов, замечаний")
public class RecommendationTemplateController {

    private final RecommendationTemplateService service;

    @Operation(summary = "Новая рекомендация")
    @PostMapping
    public ResponseEntity<RecommendationTemplateDto> save(
                                     @RequestBody @Valid
                                     @Parameter(name = "Рекомендация") NewRecommendationTemplateDto recommendationDto) {
        return ResponseEntity.ok().body(service.save(recommendationDto));
    }

    @Operation(summary = "Изменение рекомендации")
    @PatchMapping
    public ResponseEntity<RecommendationTemplateDto> update(
                                  @RequestBody @Valid
                                  @Parameter(name = "Рекомендация") UpdateRecommendationTemplateDto recommendationDto) {
        return ResponseEntity.ok().body(service.update(recommendationDto));
    }

    @Operation(summary = "Получить рекомендации по типу объекта")
    @GetMapping("/{objectTypeId}")
    public ResponseEntity<List<RecommendationTemplateDto>> getAll(@PathVariable @NotNull @Positive Long objectTypeId) {
        return ResponseEntity.ok().body(service.getAll(objectTypeId));
    }

    @Operation(summary = "Удалить рекомендацию")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Рекомендация удалена.");
    }
}