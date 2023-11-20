package ru.nabokovsg.templates.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report_templates")
public class ReportTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "object_type_id")
    private Long objectTypeId;
    @Column(name = "reporting_document_id")
    private Long reportingDocumentId;
    @OneToOne
    @JoinColumn(name = "page_title_id", referencedColumnName = "id")
    private PageTitleTemplate pageTitle;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "report_section_templates",
            joinColumns = {@JoinColumn(name = "report_id")},
            inverseJoinColumns = {@JoinColumn(name = "section_id")})
    @ToString.Exclude
    private List<SectionTemplate> sections;
}