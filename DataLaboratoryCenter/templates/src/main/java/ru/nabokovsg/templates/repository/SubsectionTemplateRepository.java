package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.templates.models.SubsectionTemplate;

public interface SubsectionTemplateRepository extends JpaRepository<SubsectionTemplate, Long> {

    @Query("select s from SubsectionTemplate s inner join ProtocolTemplate p where s.subsectionName = ?1 and p.id = ?2")
    SubsectionTemplate findBySubsectionNameAndProtocolId(String subsectionName, Long protocolId);
}