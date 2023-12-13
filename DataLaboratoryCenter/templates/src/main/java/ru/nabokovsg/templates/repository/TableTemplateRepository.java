package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.templates.models.TableTemplate;
import ru.nabokovsg.templates.models.enums.TableDataType;

public interface TableTemplateRepository extends JpaRepository<TableTemplate, Long> {

    TableTemplate findByTableDataTypeAndObjectTypeIdAndReportingDocumentId(TableDataType tableDataType
                                                                         , Long objectTypeId
                                                                         , Long reportingDocumentId);

    TableTemplate findByTableDataType(TableDataType tableDataType);
}