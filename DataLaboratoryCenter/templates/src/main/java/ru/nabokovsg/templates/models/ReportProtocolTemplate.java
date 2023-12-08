package ru.nabokovsg.templates.models;

import lombok.*;
import ru.nabokovsg.templates.models.enums.ProtocolType;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report_protocol_templates")
public class ReportProtocolTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "object_type_id")
    private Long objectTypeId;
    @Column(name = "reporting_document_id")
    private Long reportingDocumentId;
    @Column(name = "protocol_type")
    @Enumerated(EnumType.STRING)
    private ProtocolType protocolType;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "protocol_name")
    private String protocolName;
    @Column(name = "protocol_title")
    private String protocolTitle;
    @Column(name = "text")
    private String protocolText;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "report_protocol_subsection_templates",
            joinColumns = {@JoinColumn(name = "protocol_id")},
            inverseJoinColumns = {@JoinColumn(name = "subsections_id")})
    @ToString.Exclude
    private List<SubsectionTemplate> subsections;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "report_protocol_table_templates",
            joinColumns = {@JoinColumn(name = "protocol_id")},
            inverseJoinColumns = {@JoinColumn(name = "table_id")})
    @ToString.Exclude
    private List<TableTemplate> tables;
//    @OneToOne
//    @JoinColumn(name = "conclusions_template_id", referencedColumnName = "id")
//    private ConclusionTemplate conclusions;
}
