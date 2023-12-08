package ru.nabokovsg.templates.models;

import lombok.*;
import ru.nabokovsg.templates.models.enums.ProtocolType;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "protocol_templates")
public class ProtocolTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "protocol_type")
    @Enumerated(EnumType.STRING)
    private ProtocolType protocolType;
    @Column(name = "object_type_id")
    private Long objectTypeId;
    @Column(name = "reporting_document_id")
    private Long reportingDocumentId;
    @Column(name = "document_name")
    private String documentName;
    @Column(name = "document_title")
    private String documentTitle;
    @OneToOne
    @JoinColumn(name = "header_id", referencedColumnName = "id")
    private HeaderTemplate header;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "protocol_subsection_templates",
            joinColumns = {@JoinColumn(name = "protocol_id")},
            inverseJoinColumns = {@JoinColumn(name = "subsection_id")})
    @ToString.Exclude
    private Set<SubsectionTemplate> subsections;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "protocol_tables_templates",
            joinColumns = {@JoinColumn(name = "protocol_id")},
            inverseJoinColumns = {@JoinColumn(name = "table_id")})
    @ToString.Exclude
    private Set<TableTemplate> tables;
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "protocol_templates_recommendations",
//            joinColumns = {@JoinColumn(name = "protocol_id")},
//            inverseJoinColumns = {@JoinColumn(name = "recommendation_id")})
//    @ToString.Exclude
//    private List<RecommendationTemplate> recommendations;
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "protocol_templates_appendices",
//            joinColumns = {@JoinColumn(name = "protocol_id")},
//            inverseJoinColumns = {@JoinColumn(name = "appendices_id")})
//    @ToString.Exclude
//    private List<AppendicesTemplates> appendices;
}