package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.templates.models.SectionTemplate;

public interface SectionTemplateRepository extends JpaRepository<SectionTemplate, Long> {
}