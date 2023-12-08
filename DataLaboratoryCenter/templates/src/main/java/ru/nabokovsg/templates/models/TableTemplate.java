package ru.nabokovsg.templates.models;

import lombok.*;
import ru.nabokovsg.templates.models.enums.ProtocolType;
import ru.nabokovsg.templates.models.enums.SubsectionDataType;
import ru.nabokovsg.templates.models.enums.TableDataType;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "table_templates")
public class TableTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "protocol_type")
    @Enumerated(EnumType.STRING)
    private ProtocolType protocolType;
    @Column(name = "subsection_data_type")
    @Enumerated(EnumType.STRING)
    private SubsectionDataType subsectionDataType;
    @Column(name = "table_data_type")
    @Enumerated(EnumType.STRING)
    private TableDataType tableDataType;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "table_name")
    private String tableName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "table_columns_headers_templates",
            joinColumns = {@JoinColumn(name = "table_id")},
            inverseJoinColumns = {@JoinColumn(name = "columns_header_id")})
    @ToString.Exclude
    private List<ColumnHeaderTemplate> columnHeaders;
}