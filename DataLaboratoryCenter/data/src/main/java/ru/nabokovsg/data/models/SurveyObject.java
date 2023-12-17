package ru.nabokovsg.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "survey_objects")
public class SurveyObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "object_type_id", referencedColumnName = "id")
    private ObjectsType objectType;
    @Column(name = "stationary_number")
    private Integer stationaryNumber;
    @Column(name = "volume")
    private Integer volume;
    @OneToMany(mappedBy = "objectSurvey", fetch = FetchType.LAZY)
    private List<SurveyObjectElementData> elements;
    @ManyToOne
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private Building building;
}