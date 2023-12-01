package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.templates.models.SubsectionTemplateData;
import ru.nabokovsg.templates.models.enums.SubsectionDataType;

import java.util.List;
import java.util.Set;

public interface SubsectionTemplateDataRepository extends JpaRepository<SubsectionTemplateData, Long> {

    Set<SubsectionTemplateData> findAllBySubsectionIdAndSubsectionDataType(Long subsectionId
                                                                         , SubsectionDataType subsectionDataType);

    @Query("select s.id from SubsectionTemplateData s where s.subsectionDataType =?1")
    List<Long> findAllIdSubsectionDataType(SubsectionDataType subsectionsDataType);
}
