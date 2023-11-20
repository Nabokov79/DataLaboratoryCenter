package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.data.models.SurveyObjectElement;

public interface ObjectsSurveyElementRepository extends JpaRepository<SurveyObjectElement, Long> {
}