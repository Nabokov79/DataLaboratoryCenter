package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.templates.models.SubsectionTemplate;
import ru.nabokovsg.templates.models.enums.SubsectionDataType;

public interface SubsectionTemplateRepository extends JpaRepository<SubsectionTemplate, Long> {

    SubsectionTemplate findBySubsectionDataType(SubsectionDataType subsectionDataType);
}