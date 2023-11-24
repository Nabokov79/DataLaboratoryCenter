package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.templates.models.SubsectionTemplateData;

import java.util.List;
import java.util.Set;

public interface SubsectionTemplateDataRepository extends JpaRepository<SubsectionTemplateData, Long> {

    @Query("select s from SubsectionTemplateData s where s.subsectionData in :subsectionsData")
    Set<SubsectionTemplateData> findAllBySubsectionData(@Param("subsectionsData") List<String> subsectionsData);

    @Query("select s.id from SubsectionTemplateData s where s.subsectionDataType =?1")
    List<Long> findAllIdSubsectionDataType(String subsectionsDataType);
}
