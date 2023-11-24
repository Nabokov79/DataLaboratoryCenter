package ru.nabokovsg.templates.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subsection_data_templates")
public class SubsectionTemplateData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "subsection_data_type")
    private String subsectionDataType;
    @Column(name = "subsection_data")
    private String subsectionData;

    @Override
    public String toString() {
        return "SubsectionDataTemplate{" +
                "id=" + id +
                ", subsectionDataType='" + subsectionDataType + '\'' +
                ", subsectionData='" + subsectionData + '\'' +
                '}';
    }
}