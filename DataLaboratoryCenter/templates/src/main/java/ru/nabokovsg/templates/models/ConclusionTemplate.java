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
@Table(name = "conclusion_templates")
public class ConclusionTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "object_type_id")
    private Long objectTypeId;
    @Column(name = "reporting_document_id")
    private Long reportingDocumentId;
    @Column(name = "if_than_norm")
    private String ifThanNorm;
    @Column(name = "approaching")
    private String approaching;
    @Column(name = "equal")
    private String equal;
    @Column(name = "if_less_norm")
    private String ifLessNorm;
    @Column(name = "if_no_norm")
    private String ifNoNorm;
}