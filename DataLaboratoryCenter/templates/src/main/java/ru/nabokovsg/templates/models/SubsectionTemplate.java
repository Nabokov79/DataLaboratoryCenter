package ru.nabokovsg.templates.models;

import lombok.*;
import ru.nabokovsg.templates.models.enums.SubsectionDataType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
    @Column(name = "subsection_data_type")
    @Enumerated(EnumType.STRING)
    private SubsectionDataType subsectionDataType;
    @Column(name = "sequential_number")
    private double sequentialNumber;
    @Column(name = "subsection_name")
    private String subsectionName;
    @Column(name = "subsection_text")
    private String subsectionText;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "subsection_subsection_data_templates",
            joinColumns = {@JoinColumn(name = "subsection_id")},
            inverseJoinColumns = {@JoinColumn(name = "subsection_data_id")})
    @ToString.Exclude
    private List<SubsectionTemplateData> subsectionData;
    @ManyToOne
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    private TableTemplate table;
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "subsection_templates_recommendations",
//            joinColumns = {@JoinColumn(name = "subsection_template_id")},
//            inverseJoinColumns = {@JoinColumn(name = "recommendation_id")})
//    @ToString.Exclude
//    private List<RecommendationTemplate> recommendations;
//    @OneToOne
//    @JoinColumn(name = "conclusions_template_id", referencedColumnName = "id")
//    private ConclusionTemplate conclusions;


    @Override
    public String toString() {
        return "SubsectionTemplate{" +
                "id=" + id +
                ", subsectionDataType=" + subsectionDataType +
                ", sequentialNumber=" + sequentialNumber +
                ", subsectionName='" + subsectionName + '\'' +
                ", subsectionText='" + subsectionText + '\'' +
                ", subsectionData=" + subsectionData +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubsectionTemplate that = (SubsectionTemplate) o;
        return id == that.id && Double.compare(that.sequentialNumber, sequentialNumber) == 0 && subsectionDataType == that.subsectionDataType && Objects.equals(subsectionName, that.subsectionName) && Objects.equals(subsectionText, that.subsectionText) && Objects.equals(subsectionData, that.subsectionData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subsectionDataType, sequentialNumber, subsectionName, subsectionText, subsectionData);
    }
}