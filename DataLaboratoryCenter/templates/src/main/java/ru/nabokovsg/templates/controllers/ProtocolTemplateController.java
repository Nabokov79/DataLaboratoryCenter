package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.templates.services.ProtocolTemplateService;

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
}