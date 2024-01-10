package ru.nabokovsg.data.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.data.dto.objectsSurveyElementData.NewObjectsSurveyElementDataDto;
import ru.nabokovsg.data.dto.objectsSurveyElementData.ObjectsSurveyElementDataDto;
import ru.nabokovsg.data.dto.objectsSurveyElementData.UpdateObjectsSurveyElementDataDto;
import ru.nabokovsg.data.services.ObjectsSurveyElementDataService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/objects/elements",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Элементы объекта обследования",
        description="API для работы с данными елементов объекта обследования")
public class ObjectsSurveyElementDataController {

    private final ObjectsSurveyElementDataService service;

    @Operation(summary = "Добавление данных типа объекта")
    @PostMapping
    public ResponseEntity<List<ObjectsSurveyElementDataDto>> save(
            @RequestParam @Parameter(description = "Индентификатор объекта обследования")
            @NotNull @Positive Long surveyObjectId,
            @RequestBody @Valid
            @Parameter(description = "Объект обследования") List<NewObjectsSurveyElementDataDto> elementsDataDto) {
        return ResponseEntity.ok().body(service.save(surveyObjectId, elementsDataDto));
    }

    @Operation(summary = "Изменение данных типа объекта")
    @PatchMapping
    public ResponseEntity<List<ObjectsSurveyElementDataDto>> update(
            @RequestParam @Parameter(description = "Индентификатор объекта обследования")
            @NotNull @Positive Long surveyObjectId,
            @RequestBody @Valid
            @Parameter(description = "Объект обследования") List<UpdateObjectsSurveyElementDataDto> elementsDataDto) {
        return ResponseEntity.ok().body(service.update(surveyObjectId, elementsDataDto));
    }

    @Operation(summary = "Удаление элемента")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор элемента") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Элемент был удален");
    }
}