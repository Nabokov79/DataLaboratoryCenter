package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.templates.models.SubsectionTemplateData;
import ru.nabokovsg.templates.models.enums.SubsectionDataType;

import java.util.List;
import java.util.Set;

public interface SubsectionTemplateDataRepository extends JpaRepository<SubsectionTemplateData, Long> {

    SubsectionTemplateData findBySubsectionDataTypeAndDivisionId(SubsectionDataType subsectionDataType
                                                               , Long divisionId);

    Set<SubsectionTemplateData> findAllBySubsectionDataTypeAndObjectTypeId(SubsectionDataType subsectionDataType
                                                                         , Long objectTypeId);

    @Query("select s" +
          " from SubsectionTemplateData s" +
          " where s.subsectionDataType in :subsectionsDataType and s.subsectionData in :tolls")
    Set<SubsectionTemplateData> findAllByToll(@Param("subsectionsDataType") List<String> subsectionsDataType
                                            , @Param("tolls") List<String> tolls);
}