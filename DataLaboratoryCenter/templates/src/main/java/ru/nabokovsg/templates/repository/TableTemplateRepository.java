package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.templates.models.TableTemplate;

public interface TableTemplateRepository extends JpaRepository<TableTemplate, Long> {
}