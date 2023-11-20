package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.data.models.SurveyObject;

public interface SurveyObjectRepository extends JpaRepository<SurveyObject, Long> {
}