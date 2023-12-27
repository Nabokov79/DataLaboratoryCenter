package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.templates.models.AppendicesTemplate;

import java.util.Set;

public interface AppendicesTemplateRepository extends JpaRepository<AppendicesTemplate, Long> {

    AppendicesTemplate findByAppendicesName(String appendicesName);

    Set<AppendicesTemplate> findByObjectTypeId(Long objectTypeId);
}