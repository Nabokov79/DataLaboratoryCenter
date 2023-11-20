package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.templates.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.UpdatePageTitleTemplateDto;
import ru.nabokovsg.templates.models.HeaderTemplate;
import ru.nabokovsg.templates.models.PageTitleTemplate;

@Mapper(componentModel = "spring")
public interface PageTitleTemplateMapper {

    PageTitleTemplate mapToNewPageTitleTemplate(NewPageTitleTemplateDto pageTitleDto);

    PageTitleTemplate mapToUpdatePageTitleTemplate(UpdatePageTitleTemplateDto pageTitleDto);

    @Mapping(source = "pageTitle.header", target = "header")
    @Mapping(source = "signature", target = "signatureString")
    @Mapping(source = "pageTitle.year", target = "year")
    @Mapping(target = "pageTitle.id", ignore = true)
    PageTitleTemplate mapToPageTitleTemplate(@MappingTarget PageTitleTemplate pageTitle
                                                          , HeaderTemplate header
                                                          , String signature
                                                          , String year);

    PageTitleTemplateDto mapToPageTitleTemplateDto(PageTitleTemplate pageTitle);
}