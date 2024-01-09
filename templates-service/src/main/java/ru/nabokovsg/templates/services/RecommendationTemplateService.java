package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.recommendation.NewRecommendationTemplateDto;
import ru.nabokovsg.templates.dto.recommendation.RecommendationTemplateDto;
import ru.nabokovsg.templates.dto.recommendation.UpdateRecommendationTemplateDto;
import ru.nabokovsg.templates.models.RecommendationTemplate;
import ru.nabokovsg.templates.models.enums.DataType;

import java.util.List;
import java.util.Set;

public interface RecommendationTemplateService {

    RecommendationTemplateDto save(DataType type, NewRecommendationTemplateDto recommendationDto);

    RecommendationTemplateDto update(UpdateRecommendationTemplateDto recommendationDto);

    List<RecommendationTemplateDto> getAll(Long objectTypeId);

    void delete(Long id);
}