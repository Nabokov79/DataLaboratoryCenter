package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.templates.models.SubsectionDataTemplate;

public interface SubsectionDataTemplateRepository extends JpaRepository<SubsectionDataTemplate, Long> {
}