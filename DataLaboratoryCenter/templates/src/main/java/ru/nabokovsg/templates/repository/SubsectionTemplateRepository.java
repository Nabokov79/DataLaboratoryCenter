package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.templates.models.SubsectionTemplate;

public interface SubsectionTemplateRepository extends JpaRepository<SubsectionTemplate, Long> {
}