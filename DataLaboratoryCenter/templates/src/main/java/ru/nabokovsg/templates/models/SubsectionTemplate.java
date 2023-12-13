package ru.nabokovsg.templates.models;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subsection_templates")
public class SubsectionTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "sequential_number")
    private Double sequentialNumber;
    @Column(name = "subsection_name")
    private String subsectionName;
    @Column(name = "text")
    private String text;
    @Column(name = "certificate_employee")
    private String certificateEmployee;
    @Column(name = "division_data")
    private String divisionData;
    @ManyToOne
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    private TableTemplate table;
    @Column(name = "text_before_table")
    private String textBeforeTable;
    @Column(name = "text_after_table")
    private String textAfterTable;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "subsection_subsection_data_templates",
            joinColumns = {@JoinColumn(name = "subsection_id")},
            inverseJoinColumns = {@JoinColumn(name = "subsection_data_id")})
    @ToString.Exclude
    private List<SubsectionDataTemplate> subsectionData;
}