package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.templates.models.SubsectionTemplate;

public interface SubsectionTemplateRepository extends JpaRepository<SubsectionTemplate, Long> {

    @Query("select s from SubsectionTemplate s where s.subsectionName = ?1 and SectionTemplate.id = ?2")
    SubsectionTemplate findBySubsectionNameAndSectionId(String subsectionName, Long sectionId);

    @Query("select s from SubsectionTemplate s where s.subsectionName = ?1 and ProtocolTemplate.id = ?2")
    SubsectionTemplate findBySubsectionNameAndProtocolId(String subsectionName, Long protocolId);
}