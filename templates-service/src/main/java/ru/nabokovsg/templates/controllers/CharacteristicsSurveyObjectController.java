package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.templates.dto.characteristics.CharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.dto.characteristics.NewCharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.dto.characteristics.UpdateCharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.services.CharacteristicsSurveyObjectService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(
        value = "/template/characteristics",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
@Tag(name="Шаблон характеристики объекта",
        description="API для работы с данными шаблона характеристики объекта")
public class CharacteristicsSurveyObjectController {

    private final CharacteristicsSurveyObjectService service;

    @Operation(summary = "Данные шаблона новых характеристик объекта")
    @PostMapping("/section")
    public ResponseEntity<List<CharacteristicsSurveyObjectDto>> saveFromSectionTemplate(
            @RequestParam(name = "sectionId")
            @NotNull @Positive @Parameter(name = "Индентификатор раздела") Long sectionId,
            @RequestBody @Valid
            @Parameter(name = "Характеристики объекта") List<NewCharacteristicsSurveyObjectDto> characteristicsDto) {
        return ResponseEntity.ok().body(service.save("section", sectionId, characteristicsDto));
    }

    @Operation(summary = "Данные шаблона новых характеристик объекта")
    @PostMapping("/protocol")
    public ResponseEntity<List<CharacteristicsSurveyObjectDto>> saveFromProtocolTemplate(
            @RequestParam(name = "protocolId")
            @NotNull @Positive @Parameter(name = "Индентификатор протокола") Long protocolId,
            @RequestBody @Valid
            @Parameter(name = "Характеристики объекта") List<NewCharacteristicsSurveyObjectDto> characteristicsDto) {
        return ResponseEntity.ok().body(service.save("protocol", protocolId, characteristicsDto));
    }

    @Operation(summary = "Изменение данных характеристик объекта")
    @PatchMapping
    public ResponseEntity<List<CharacteristicsSurveyObjectDto>> update(
            @RequestBody @Valid
            @Parameter(name = "Характеристики объекта") List<UpdateCharacteristicsSurveyObjectDto> characteristicsDto) {
        return ResponseEntity.ok().body(service.update(characteristicsDto));
    }

    @Operation(summary = "Получение всехшаблонов характеристик объекта")
    @GetMapping
    public ResponseEntity<List<CharacteristicsSurveyObjectDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удалить заключения")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Характеристика удалена.");
    }
}