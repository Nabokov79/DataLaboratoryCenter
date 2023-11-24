package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.templates.models.SubsectionTemplate;

import java.util.List;
import java.util.Set;

public interface SubsectionTemplateRepository extends JpaRepository<SubsectionTemplate, Long> {

    @Query("select s from SubsectionTemplate s where s.sectionId in :sectionIds and s.subsectionDataType in :dataType")
    Set<SubsectionTemplate> findAllBySectionIdAndSubsectionDataType(@Param("sectionIds") List<Long> sectionIds, @Param("dataType") List<String> dataType);

    Set<SubsectionTemplate> findAllBySectionId(Long sectionId);
}