package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.templates.dto.protocol.NewProtocolTemplateDto;
import ru.nabokovsg.templates.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.templates.dto.protocol.ShortProtocolTemplateDto;
import ru.nabokovsg.templates.dto.protocol.UpdateProtocolTemplateDto;
import ru.nabokovsg.templates.services.ProtocolTemplateService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(
        value = "/template/protocol",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон протокола/заключения",
        description="API для работы с данными шаблона протокола/заключения")
public class ProtocolTemplateController {

    private final ProtocolTemplateService service;

    @Operation(summary = "Данные шаблона нового протокола/заключения")
    @PostMapping
    public ResponseEntity<ShortProtocolTemplateDto> save(@RequestBody @Valid NewProtocolTemplateDto protocolDto) {
        return ResponseEntity.ok().body(service.save(protocolDto));
    }

    @Operation(summary = "Данные для изменения информации в протокола/заключения")
    @PatchMapping
    public ResponseEntity<ShortProtocolTemplateDto> update(@RequestBody @Valid UpdateProtocolTemplateDto protocolDto) {
        return ResponseEntity.ok().body(service.update(protocolDto));
    }

    @Operation(summary = "Получить краткие данные протоколов/заключений")
    @GetMapping("/all")
    public ResponseEntity<List<ShortProtocolTemplateDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Добавить шаблон заключения к шаблону протокола/заключения")
    @GetMapping("/{id}/conclusion")
    public ResponseEntity<ProtocolTemplateDto> addConclusion(@PathVariable @NotNull @Positive Long id) {
        return ResponseEntity.ok().body(service.addConclusion(id));
    }

    @Operation(summary = "Добавить шаблон заключения к шаблону протокола/заключения")
    @GetMapping("/{id}/appendices")
    public ResponseEntity<ProtocolTemplateDto> addAppendices(@PathVariable @NotNull @Positive Long id) {
        return ResponseEntity.ok().body(service.addAppendices(id));
    }

    @Operation(summary = "Добавить шаблон заключения к шаблону протокола/заключения")
    @GetMapping("/{id}/recommendation")
    public ResponseEntity<ProtocolTemplateDto> addRecommendation(@PathVariable @NotNull @Positive Long id) {
        return ResponseEntity.ok().body(service.addRecommendation(id));
    }
}