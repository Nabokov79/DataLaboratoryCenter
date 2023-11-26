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
@Table(name = "passport_data_templates")
public class PassportDataTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "sequential_number")
    private String sequentialNumber;
    @Column(name = "data_name")
    private String dataName;
    @Column(name = "apply_report")
    private Boolean applyReport;
    @Column(name = "apply_protocol")
    private Boolean applyProtocol;
}