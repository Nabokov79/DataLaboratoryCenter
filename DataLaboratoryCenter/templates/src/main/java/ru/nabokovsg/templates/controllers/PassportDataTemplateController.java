package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.templates.dto.passportData.NewPassportDataTemplateDto;
import ru.nabokovsg.templates.dto.passportData.PassportDataTemplateDto;
import ru.nabokovsg.templates.dto.passportData.UpdatePassportDataTemplateDto;
import ru.nabokovsg.templates.services.PassportDataTemplateService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(
        value = "/template/passport",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон паспортных данных",
        description="API для работы с шаблоном паспортных данных")
public class PassportDataTemplateController {

    private final PassportDataTemplateService service;

    @Operation(summary = "Добавить шаблон паспортных данных")
    @PostMapping
    public ResponseEntity<List<PassportDataTemplateDto>> save(
                                                            @RequestBody @Valid
                                                            @Parameter(description = "Данные шаблона паспортных данных")
                                                            List<NewPassportDataTemplateDto> passportDataDto) {
        return ResponseEntity.ok().body(service.save(passportDataDto));
    }

    @Operation(summary = "Добавить шаблон паспортных данных")
    @PatchMapping
    public ResponseEntity<List<PassportDataTemplateDto>> update(
                                                            @RequestBody @Valid
                                                            @Parameter(description = "Данные шаблона паспортных данных")
                                                            List<UpdatePassportDataTemplateDto> passportDataDto) {
        return ResponseEntity.ok().body(service.update(passportDataDto));
    }

    @Operation(summary = "Получить шаблоны пасспортных данных для конкретного типа объектов")
    @GetMapping
    public ResponseEntity<List<PassportDataTemplateDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }
}