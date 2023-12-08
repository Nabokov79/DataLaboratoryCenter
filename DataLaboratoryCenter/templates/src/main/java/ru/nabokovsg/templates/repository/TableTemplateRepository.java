package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.templates.models.TableTemplate;
import ru.nabokovsg.templates.models.enums.ProtocolType;
import ru.nabokovsg.templates.models.enums.SubsectionDataType;
import ru.nabokovsg.templates.models.enums.TableDataType;

public interface TableTemplateRepository extends JpaRepository<TableTemplate, Long> {

    TableTemplate findByProtocolTypeAndTableDataType(ProtocolType protocolType, TableDataType tableDataType);

    TableTemplate findBySubsectionDataType(SubsectionDataType subsectionDataType);
}