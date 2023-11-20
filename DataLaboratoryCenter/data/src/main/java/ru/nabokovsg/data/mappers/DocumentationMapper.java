package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.documentation.DocumentationDto;
import ru.nabokovsg.data.dto.documentation.NewDocumentationDto;
import ru.nabokovsg.data.dto.documentation.UpdateDocumentationDto;
import ru.nabokovsg.data.models.Documentation;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentationMapper {

    List<Documentation> mapToNewDocumentations(List<NewDocumentationDto> documentationsDto);

    List<Documentation> mapToUpdateDocumentation(List<UpdateDocumentationDto> documentationsDto);

    List<DocumentationDto> mapToDocumentationDto(List<Documentation> documentations);
}