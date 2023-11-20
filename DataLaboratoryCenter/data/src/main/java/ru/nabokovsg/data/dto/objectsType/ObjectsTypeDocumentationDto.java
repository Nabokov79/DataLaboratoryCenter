package ru.nabokovsg.data.dto.objectsType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.data.dto.documentation.DocumentationDto;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные типа объекта и нормативно-технических документов")
public class ObjectsTypeDocumentationDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название объекта")
    private String objectName;
    @Schema(description = "Модель объекта")
    private String model;
    @Schema(description = "Объем объекта")
    private Integer volume;
    @Schema(description = "Положение объекта")
    private String orientation;
    @Schema(description = "Нормативно-техническая документация объекта")
    private List<DocumentationDto> documentations;
}