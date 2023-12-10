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
@Table(name = "appendices_templates")
public class AppendicesTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "object_type_id")
    private Long objectTypeId;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "appendices_name")
    private String appendicesName;
}