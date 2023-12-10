package ru.nabokovsg.templates.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "section_templates")
public class SectionTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "object_type_id")
    private Long objectTypeId;
    @Column(name = "reporting_document_id")
    private Long reportingDocumentId;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "section_name")
    private String sectionName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "section_subsection_templates",
            joinColumns = {@JoinColumn(name = "section_id")},
            inverseJoinColumns = {@JoinColumn(name = "subsection_id")})
    @ToString.Exclude
    private Set<SubsectionTemplate> subsections;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "section_protocol_templates",
            joinColumns = {@JoinColumn(name = "section_id")},
            inverseJoinColumns = {@JoinColumn(name = "protocol_id")})
    @ToString.Exclude
    private List<ReportProtocolTemplate> protocols;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "section_recommendation_templates",
            joinColumns = {@JoinColumn(name = "section_id")},
            inverseJoinColumns = {@JoinColumn(name = "recommendation_id")})
    @ToString.Exclude
    private List<RecommendationTemplate> recommendations;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "section_appendices_templates",
            joinColumns = {@JoinColumn(name = "section_id")},
            inverseJoinColumns = {@JoinColumn(name = "appendices_id")})
    @ToString.Exclude
    private List<AppendicesTemplate> appendices;
}