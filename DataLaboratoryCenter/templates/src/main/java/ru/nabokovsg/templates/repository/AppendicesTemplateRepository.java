package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.templates.models.AppendicesTemplate;

public interface AppendicesTemplateRepository extends JpaRepository<AppendicesTemplate, Long> {

    AppendicesTemplate findByAppendicesName(String appendicesName);
}