package ru.nabokovsg.templates.models;

import lombok.*;

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
    @Column(name = "reporting_document_id")
    private Long reportingDocumentId;
    @Column(name = "object_type_id")
    private Long objectTypeId;
    @Column(name = "title")
    private String title;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "heading")
    private String heading;
    @Column(name = "text_after_heading")
    private String textAfterHeading;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "report_protocol_subsection_templates",
            joinColumns = {@JoinColumn(name = "protocol_id")},
            inverseJoinColumns = {@JoinColumn(name = "subsection_id")})
    @ToString.Exclude
    private List<SubsectionTemplate> subsections;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "report_protocol_table_templates",
            joinColumns = {@JoinColumn(name = "protocol_id")},
            inverseJoinColumns = {@JoinColumn(name = "table_id")})
    @ToString.Exclude
    private List<TableTemplate> tables;
    @OneToOne
    @JoinColumn(name = "conclusion_template_id", referencedColumnName = "id")
    private ConclusionTemplate conclusions;
}