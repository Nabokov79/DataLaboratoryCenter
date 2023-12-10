package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.recommendation.NewRecommendationTemplateDto;
import ru.nabokovsg.templates.dto.recommendation.RecommendationTemplateDto;
import ru.nabokovsg.templates.dto.recommendation.UpdateRecommendationTemplateDto;

import java.util.List;

public interface RecommendationTemplateService {

    RecommendationTemplateDto save(NewRecommendationTemplateDto recommendationDto);

    RecommendationTemplateDto update(UpdateRecommendationTemplateDto recommendationDto);

    List<RecommendationTemplateDto> getAll(Long objectTypeId);
}