package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.element.ElementDto;
import ru.nabokovsg.data.dto.element.NewElementDto;
import ru.nabokovsg.data.dto.element.UpdateElementDto;
import ru.nabokovsg.data.models.Element;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ElementMapper {

    Element mapToNewElement(NewElementDto elementDto);

    Element mapToUpdateElement(UpdateElementDto elementDto);

    List<ElementDto> mapToElementDto(List<Element> elements);
}