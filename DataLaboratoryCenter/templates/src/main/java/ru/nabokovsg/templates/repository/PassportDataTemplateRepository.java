package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.templates.models.PassportDataTemplate;

import java.util.List;
import java.util.Set;

public interface PassportDataTemplateRepository extends JpaRepository<PassportDataTemplate, Long> {

    @Query("select p from PassportDataTemplate p where p.sequentialNumber in :sequentialNumbers and p.dataName in :dataNames")
    Set<PassportDataTemplate> findAllByDataName(@Param("sequentialNumbers") List<String> sequentialNumbers
                                              , @Param("dataNames") List<String> dataNames);
}